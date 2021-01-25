## SMS Forward Application

### Features
Reading sms history filtered by date and phone number.  
Forwarding to email as a text or .csv file.   

### Build
Add `EmailInfo` class in _app/src/main/java/com/vanderkast/smsforward/email_handler_ with following `public static fields`:  
1. `address` - Address of email, that will be used to send sms history.  
2. `password` - Password of set email.  