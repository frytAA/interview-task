App for loan applications

Tech requirements:

Spring Boot REST interface

available operations are:

* apply for loan (term, amount)
  if application is not within amount/term range then reject application if application is between 00:00 and 06:00 and
  max amount is asked then reject application issued loan has 10% of principal (not 10% per year)
* extend loan extension term is preconfigured. Upon extension the due date is changed, original due date + term
* fetch loan should return amount, due date

define max/min amount and max/min term (days)
no installments junit tests integration success path scenario test (loan issued)
no GUI no authorization no users

Non-tech requirements:

* code has to be easy to extend/modify
* SOLID, KISS, DRY, etc
* post the solution to a public repo (github, bitbucket, etc)

---------------------------------------------------------------

Application can be tested using swagger: http://localhost:8080/swagger-ui/index.html

H2 database: http://localhost:8080/h2-console/

New Validation can be easily added using interface LoanApplicationValidation

Validation parameters are configurable in application.yml which can be set as environment variable without code changes.

LoanIntegrationTest - covers full scenario with creation, extension and retrieval
