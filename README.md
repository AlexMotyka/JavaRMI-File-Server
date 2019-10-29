# assignment2fall2019-AlexMotyka

## File Server(Google Drive 2.0)

This is file server with two components: the client and server.

The server consists of a server class, implemenation class, interface class and makes use of Java RMI to makes it's methods available remotely. The server has the following functionalities:

- authenticate(String password): authenticates the user if the provided password is correct. Server methods can not be accessed with authenticating first.
- downloadFile(String filename): allows user to download the specified remote file
- uploadFile(String filename, byte[] upFile): allows the user to upload a local file to the server
- deleteFile(String filename): allows the user to delete a remote file
- writeFile(String filename, String line): allows the user to write to a remote file. if the file already exists the string is appended to it. If the file does not already exist it is created and the string is written to it.
- listFiles(): return a list of all the server files to the client

Each method has complete error handling so that if the client requests a non-existing file or enters a command incorrectly the server will handle it and the connection will not fail.

The client consists of a client class, and an interface class. It uses Java RMI to access the server methods. The client must first authenticate in order to access the server functions. Once authenticated the client listens for user input and using this input determines which remote method to call.

**Running the code**

To run the code locally you must have three terminals open:
- With terminal 1 `cd` into the server folder and run `rmiregistry`
- With terminal 2 `cd` into the server folder and run `javac` to compile each class. Once compiled run `java Server` to run the server
- With terminal 3 `cd` into the client folder and run `javac` to compile each class. Once compiled run `java Client` to run the client. Follow the prompts from the client console.


