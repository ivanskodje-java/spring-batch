# Spring Batch Project
WIP Test Project, using Spring Batch. Clone on your own risk!

## H2 Database
Access H2 Console: http://localhost:8080/h2-console

> JDBC URL: jdbc:h2:~/cvs-to-database  
> Username: sa  
> Password: <blank>

## Time Tests
### Test 1 (current commit)
Step: [myStep] executed in 5m20s418ms
Job: [SimpleJob: [name=myJob]] completed with the following parameters: [{time=1600013373557}] and the following status: [COMPLETED] in 5m20s487ms

9231 entries into COMPANY
9521 entries into EMPLOYEE
9521 entries into EMPLOYEE_HASH

> Notes: Now that we know it "works", we can think about improving the algorithm for insertion. We are currently wasting a lot of time inserting "one and one". :)

### Test 1 (Rerun)
Step: [myStep] executed in 1s171ms
Job: [SimpleJob: [name=myJob]] completed with the following parameters: [{time=1600014284356}] and the following status: [COMPLETED] in 1s173ms

(We did not insert anything, since we have already stored the data we received from the CSV file. Very Good.)

----

## CVS Sample Data 

> /sample-data/samplecsv20k.csv

Downloaded 20k Records CVS Test sample from https://www.appsloveworld.com/sample-csv-file/



## LICENSE

MIT License (MIT)

Copyright (c) 2020 Ivan P. Skodje

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 



