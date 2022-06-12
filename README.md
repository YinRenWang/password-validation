# Password-validation

Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice). The service is meant to check a text string for compliance to any number of password validation rules. The rules currently known are:

### Problem
1. Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
2. Must be between 5 and 12 characters in length.
3. Must not contain any sequence of characters immediately followed by the same sequence.

----

# How to work 

This project is use maven and springBoot complete,  reference commands running server

```
$> cd password-service
$> mvn clean install
$> mvn spring-boot:run
```

The server API document writing by Swagger UI <br>
The default path is [http://localhost:8080/swagger-ui.html](http://localhot:8080/swagger-ui.html) <br>
with the visual documentation making it easy for back end implementation and client side consumption. <br>

----

# How to design

## ValidationRule

<code>ValidationRule</code> is a interfocae we can Implements to create new validation rule.  <br>

<code>DigitsRule</code> is validation value have contains digits (ex:0-9). <br>
<code>LowerCaseLetterRule</code> is validation value have contains lower case (ex:a-z). <br>
<code>LengthRule</code> is validation value length within limits.  <br>
<code>SequenceNotRepeatRule</code> is validation value not contain any sequence of characters immediately followed by the same sequence.  <br>
