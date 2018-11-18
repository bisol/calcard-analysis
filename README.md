# creditAnalysisService
This is the second part of an implementation of the credit analysis service using microservices. 
The first part is [here](https://github.com/bisol/calcard-gw). 

This project contains the credit analysis service. It receives credit proposals via messaging and runs a hand 
crafted algorithm to aprove or reject them. Provided sample data were inclueded in the test cases.

For more info, see [here](https://github.com/bisol/calcard-gw). 

## Building the docker image:

To optimize the calcard application for production, run:

    ./mvnw package -Pprod jib:dockerBuild


## About

This application was generated using JHipster 5.7.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.7.0](https://www.jhipster.tech/documentation-archive/v5.7.0).
There is a LOT of generated code. If you want to check the code, these are the most heavily edited files:

```
src/main/java/com/bisol/calcard/service/CreditAnalysisService.java
src/test/java/com/bisol/calcard/service/CreditAnalysisServiceIntTest.java
```

[JHipster Homepage and latest documentation]: https://www.jhipster.tech
