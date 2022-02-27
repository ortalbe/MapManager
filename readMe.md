# Getting Started

### Reference Documentation

I used JGraphT for searching the shortest path.
* [jgrapht github repo](https://github.com/jgrapht/jgrapht.github.com)
* [jgrapht web side](https://jgrapht.org/)

for parsing the jason I used jackson library 
* [jackson github repo](https://github.com/FasterXML/jackson)

### Design explanation

I used 2 services in the app.
1. BrainService - managed all jgrapht method required - the service contain a memory cash with the graph  (in real word it will be connected to a DB).
the service is a singelton in order to avoid the population of the map every rest call.
2. MapGraphService - this is a connector between the Rest and a call to the jgrapht.

### Usage

there are 3 rest call :
1. populate initialGraph   
   end point :  localhost:8080/loadgraph  
   call type : POST  
   body : json file provide in the task
2. get shortest path   
       end point : localhost:8080/getshortestpath?source={source}&trget?{target}  
       call type : GET  
3. set weight on exist edge
    end point : localhost:8080/setweight
    call type : PUT
    Body : Link Object



####important note - 
#### 1. the first call must be /loadgraph otherwise graph will be empty and you will get an error.
#### 2. error handling - create only infrastructure  with common exception  IllegalArgumentException - need to enhanced error handling 
#### 3. Junit - coverage of 54% which is low - I wrote junit only for main service
