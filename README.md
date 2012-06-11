# guice-slf4j

SLF4J logger injector for guice framework.

## Usage
Add the following code
```java
bindListener(Matchers.any(), new Slf4jGuiceTypeListener());
```
to your `configure()` method in you module declaration.