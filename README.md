# release-manager
Demo service for storing deployment data.

### usage
There are 2 options to run the service:
* run `ReleaseManagerApplication.main` in an IDE
* run `mvn spring-boot:run` in a terminal

### db access
While the service is running, the in-memory H2 database can be accessed via http://localhost:8080/h2-console/ with the default credentials.

### curl examples
1. add deployment: `curl -X POST localhost:8080/deploy -H 'Content-Type: application/json' -d '{"name":"Service A", "version": 1}'`
2. fetch deployments: `curl localhost:8080/services?systemVersion=2`