[![Java CI with Maven](https://github.com/Ramzus/localized/actions/workflows/maven.yml/badge.svg)](https://github.com/Ramzus/localized/actions/workflows/maven.yml)
[![Maven Package](https://github.com/Ramzus/localized/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/Ramzus/localized/actions/workflows/maven-publish.yml)

Forked from https://github.com/deathman92/localized (which were also a fork from https://github.com/malkusch/localized project). 
With Maven support (yes, gradle was removed).
Now support Hibernate 5.2.2.Final. Other versions weren't tested.

# @Localized
Internationalization (i18n) is so boring. There is no one single best
practice. And they are all just a pain in the ass when your `String` is
no more a String but something like a `Map<Locale, String>`. Bye JSR-303
validation annotations. So let's try to find a solution which keeps i18n
simple, without polluting your domain model and keep the type of the 
properties:

```java
@Entity
class Book {

    @Id
    private int id;

    private Author author;

    @Localized
    private String title;

}
```

`@Localized` annotates the property `Book.title` as a translatable `String`.
This looks promising. But how does Hibernate know which `Locale` is relevant?

## LocaleResolver
That's the job of a `LocaleResolver` implementation. Implement or choose one
from the package `io.github.deathman.localized.locale_resolver`. There are several
ways of registering the LocaleResolver:

Specify the fully qualified class name in the hibernate property 
*hibernate.listeners.localized.locale_resolver*:
```xml
<property name="hibernate.listeners.localized.locale_resolver">SpringLocaleResolver</property>
``` 
Register it programmatically:
```java
LocalizedIntegrator.setLocaleResolver(new SpringLocaleResolver());
```

# Gradle
```groovy
repositories {
 jcenter()
}

dependencies {
 compile("com.ramzus.localized:localized:1.0.0")
}
```
# Maven
```xml
<dependency>
  <groupId>com.ramzus.localized</groupId>
  <artifactId>localized</artifactId>
  <version>1.0.0</version>
</dependency>
```

# Concept
The mechanism is straight forward: A `@Localized` field is backed by many `LocalizedProperty`
entities (each locale one entity). Hibernate's event system provides infrastructure for 
replacing and storing transparently the `@Localized` fields.This concept increases the Session 
communication. Do not use `@Localized` when performance is a concern.

Some concepts of original realisation have been remade. 
* Type of field 'value' is String now instead of @Lob.
* Reference by type is replaced with refenrence by table name to simplify inserting by SQL. 
* If no translation found for locale default value of field is used.

# Limitations
I18n happens around Hibernate's `POST_LOAD`, `POST_UPDATE`, `POST_INSERT` and `POST_DELETE`
events. In between nothing happens. I.e. you have to fix the locale at Session begin
(before the first `POST_LOAD` event on a localized entity). If you change the locale during
a session you have to synchronize the entities with `Session.flush()` and `Session.refresh(Object)`.

# Dependencies
* org.hibernate:hibernate-core:5.2.2.Final
* org.springframework:spring-context:4.3.2.RELEASE

Other versions weren't tested.

# Disclaimer
There is no practical knowledge about stability, scalability or performance.
Use it at your own risk! 

# Licence
[MIT](/LICENSE.md)
