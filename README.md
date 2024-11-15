# user-command-processor

## Build

To build a project use

```shell script
./bin/build.sh
```

or

```shell script
./bin/build.bin
```

## Run

```shell script
./gradlew run
```

## Input

Example of command sequence to be processed:

```
Add ("718636eb-10e2-4d94-9725-29e8ac61185e", "a1", "Robert")
Add ("30157ccb-4a4e-43f5-ac15-b567f6e58253", "a2", "Martin")
PrintAll
DeleteAll
PrintAll
```

## Output

Example of expected output:

```
[25]: User User{id=718636eb-10e2-4d94-9725-29e8ac61185e, guid=a1, name=Robert} added.
[26]: User User{id=30157ccb-4a4e-43f5-ac15-b567f6e58253, guid=a2, name=Martin} added.
[25]: All users: [User{id=718636eb-10e2-4d94-9725-29e8ac61185e, guid=a1, name=Robert}, User{id=30157ccb-4a4e-43f5-ac15-b567f6e58253, guid=a2, name=Martin}]
[26]: All users deleted.
[25]: All users: []
```

## Logs

To enable more detailed level of logs use `resources/logback.xml` config file.