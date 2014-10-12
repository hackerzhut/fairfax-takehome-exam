# Fairfax Media Take Home Exam

## Deadlock Detection

- Deadlock detection is implemented using Single Unit Resource Allocation Graph which is directed. So the processId and ResourceId must be unique in the input files as the code has been faciliated to have self-looping which is possible in graphs.

- DFS algorithm is used to find the looping/cycle between Nodes/Vertexes

- Vertex, Edge and Graph have been separated to have modularity and scalability. 


## Mathematical StatementValidator

- Implemented using a Deque

## Setup

To install all the dependencies

```
mvn clean install

mvn verify

```

## Running   

```
java com.fairfax.exam.DeadlockDetector.java data/bad_three.txt
java com.fairfax.exam.StatementValidator.java

```

## Testing

To run all the tests

```
mvn test

```

## License

[The MIT License](http://opensource.org/licenses/MIT)






