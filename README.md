# spark-todos-rest-api

[![Build Status](https://travis-ci.org/luanpotter/spark-todos-rest-api.svg?branch=master)](https://travis-ci.org/luanpotter/spark-todos-rest-api)

This is a CRUD REST api for a todo model with two fields: a Long id and a String text (text of the todo).

It uses Spark for the web application framework and Dalesbred for database access. It uses postgres database on dev environment and hsqldb on tests.

Thanks to Despegar.com for providing us with [this](https://github.com/despegar/spark-test) nice utility for testing Spark applications.

## Setup

Run

```bash
  mvn clean install
```

To download all dependencies and such, then start the API with

```bash
  mvn exec:java -Dexec.mainClass="xyz.luan.rest.todos.Main"
```

Alternatively, you can import to Intellij IDEA (via the Import maven project option) and then use the commands from the IDE to run.
