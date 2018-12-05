# scala-webcrawler

Webcrawler written in Scala

## How to run
- Clone the Repo

- To run :
    ``sbt run``
- To run tests: ``sbt test``

## Reasoning and trade offs

- The application is built using Scala and the jsoup library. Jsoup is a java library that 
lead to some weird Java to Scala conversion problems, but it was the most suited library for the 
job.
- Scala is a functional programming language and as such it is *usually* best practice 
to use immutable data structures, but for this purpose I found that to be difficult, having made use of mutable lists 
to hold data.
- The application's purpose is quite simple and Scala adds a lot of boiler plater code and long compile time. This application might be better
suited to a language like Python - but as this is for demonstration purposes Scala was used.

## What could be done with more time

- Making the application take user input for what url to crawl.
- Containerising the application in Docker.
- Output the information is a more readable fashion - JSON, XML etc.