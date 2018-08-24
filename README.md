# GPS
![alt text](https://github.com/qiufengyu21/GPS/blob/master/1.png)
![alt text](https://github.com/qiufengyu21/GPS/blob/master/2.png)
## Student
1. Click the "I AM A STUDENT" button
2. Enter your name, UnityID
3. Click SEND DATA

Your name, UnityID, current timestamp, along with your current location information(latitude/longitude) will be sent to the online mysql database.

## Professor
1. Click the "I AM A PROFESSOR" button
2. Click the "GET DATA" button (this will retrieve all the data from the online database as a JSON file)
3. Click the "SEE ATTENDANCE" button to see the attendance.

The attendance ListView will be something list this:

|    Name    | UnityID |         Time        | Distance(meters) | Present/Absent |
|:----------:|:-------:|:-------------------:|:----------------:|:--------------:|
| Qiufeng Yu |   qyu4  | 2017-03-13 16:45:21 |       5.73m      |     Present    |
|  Sam Smith |  ssmith | 2017-03-13 16:45:15 |     1024.87m     |     Absent     |
|  Joe Salad |  jsalad | 2017-03-13 16:46:31 |      10.65m      |     Present    |
