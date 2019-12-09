Tested on an AWS c5.xlarge instance with both server (java 8) and WRK2 installed. 
## Finagle
### CURL
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ curl -v "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Content-Length: 55
< Content-Type: text/plain;charset=utf-8
< 
received parameter: value1 value2 value3 value4 value5
* Connection #0 to host localhost left intact
```
### 25k requests/s (warmup)
```
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections                                           
  Thread calibration: mean lat.: 351.059ms, rate sampling interval: 2144ms
  Thread calibration: mean lat.: 349.901ms, rate sampling interval: 2123ms
  Thread calibration: mean lat.: 354.425ms, rate sampling interval: 2162ms
  Thread calibration: mean lat.: 347.003ms, rate sampling interval: 2117ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.99ms  754.00us  46.66ms   90.46%
    Req/Sec     6.25k     2.28     6.26k    38.70%
  7493955 requests in 5.00m, 0.94GB read                                                                                                                                             
Requests/sec:  24979.85                               
Transfer/sec:      3.19MB         
```
### 50k requests/s
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ wrk/wrk -t 4 -c 400 -R 50000 -d 5m "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections
  Thread calibration: mean lat.: 1.576ms, rate sampling interval: 10ms
  Thread calibration: mean lat.: 1.953ms, rate sampling interval: 10ms
  Thread calibration: mean lat.: 1.776ms, rate sampling interval: 10ms
  Thread calibration: mean lat.: 1.567ms, rate sampling interval: 10ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.63ms    1.20ms  28.94ms   84.08%
    Req/Sec    13.20k     2.34k   28.89k    72.82%
  14968542 requests in 5.00m, 1.87GB read
Requests/sec:  49895.21
Transfer/sec:      6.38MB
```
### 75k requests/s
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ wrk/wrk -t 4 -c 400 -R 75000 -d 5m "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections
  Thread calibration: mean lat.: 191.771ms, rate sampling interval: 982ms
  Thread calibration: mean lat.: 118.360ms, rate sampling interval: 567ms
  Thread calibration: mean lat.: 223.416ms, rate sampling interval: 1224ms
  Thread calibration: mean lat.: 195.423ms, rate sampling interval: 878ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.73s     1.87s    9.68s    84.56%
    Req/Sec    18.57k     1.04k   21.89k    67.13%
  22185059 requests in 5.00m,1 2.77GB read
Requests/sec:  73950.32
Transfer/sec:      9.45MB
```
## Finch
### CURL
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ curl -v "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Content-Length: 55
< Content-Type: text/plain
< 
received parameter: value1 value2 value3 value4 value5
* Connection #0 to host localhost left intact
```
### 25k requests/s (warmup)
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ wrk/wrk -t 4 -c 400 -R 25000 -d 5m "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections
  Thread calibration: mean lat.: 682.363ms, rate sampling interval: 2934ms
  Thread calibration: mean lat.: 220.660ms, rate sampling interval: 1213ms
  Thread calibration: mean lat.: 507.993ms, rate sampling interval: 2654ms
  Thread calibration: mean lat.: 165.787ms, rate sampling interval: 1025ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.57ms    0.93ms  23.02ms   75.68%
    Req/Sec     6.25k    14.62     6.34k    81.84%
  7479612 requests in 5.00m, 855.97MB read
Requests/sec:  24931.21
Transfer/sec:      2.85MB
```
### 50k requests/s 
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ wrk/wrk -t 4 -c 400 -R 50000 -d 5m "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections
  Thread calibration: mean lat.: 2.874ms, rate sampling interval: 11ms
  Thread calibration: mean lat.: 2.882ms, rate sampling interval: 11ms
  Thread calibration: mean lat.: 2.881ms, rate sampling interval: 10ms
  Thread calibration: mean lat.: 3.058ms, rate sampling interval: 12ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.70ms    3.14ms  68.99ms   92.60%
    Req/Sec    13.16k     2.14k   32.45k    78.34%
  14978324 requests in 5.00m, 1.67GB read
Requests/sec:  49927.76
Transfer/sec:      5.71MB
```
### 75k requests/s
```
ubuntu@ip-172-31-22-198:~/wrk-benchmark-master$ wrk/wrk -t 4 -c 400 -R 75000 -d 5m "http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5"
Running 5m test @ http://localhost:8080/echo?param1=value1&param2=value2&param3=value3&param4=value4&param5=value5
  4 threads and 400 connections
  Thread calibration: mean lat.: 1024.695ms, rate sampling interval: 3594ms
  Thread calibration: mean lat.: 1014.532ms, rate sampling interval: 3708ms
  Thread calibration: mean lat.: 1006.982ms, rate sampling interval: 3700ms
  Thread calibration: mean lat.: 1027.635ms, rate sampling interval: 3784ms
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    32.59s    17.57s    1.17m    59.05%
    Req/Sec    14.79k   337.00    15.72k    68.91%
  17715396 requests in 5.00m, 1.98GB read
Requests/sec:  59051.40
Transfer/sec:      6.76MB
```

