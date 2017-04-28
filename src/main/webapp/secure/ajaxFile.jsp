 <%@page import="java.io.*,java.sql.*,com.synechron.prm.util.*;" %>

<%! OutputStream fout = null;
    InputStream filecontent = null;
    String type="";
%>

<%

final String path ="d:/myfolder/";
final javax.servlet.http.Part filePart = request.getPart("photoimg");

//get content type of the file
type=filePart.getHeader("content-type");

//checking content type of file.
if( type.equals("image/jpeg") || type.equals("image/png") ||
 type.equals("image/jpg") || type.equals("image/gif") ||
type.equals("image/bmp") )

{

final String fileName = FileName.getFileName(filePart);
//copy the content of the file
try {
fout = new FileOutputStream(new File(path + File.separator + fileName));
filecontent = filePart.getInputStream();
int read = 0;
final byte[] bytes = new byte[32*1024];

while ((read =filecontent.read(bytes)) != -1) {
fout.write(bytes, 0, read);
}
fout.flush();
fout.close();
%>

<!-- displayImage is the name of the servlet which will read the image file and set it on response object-->

<img src="displayImage/<%=fileName%>" />

<% } catch (Exception e) { %>

<div style="font-size:30px; color:red">File is not successfully uploaded</div>");

<% } } else { %>

<div style="font-size:30px; color:red">Please upload image(jpeg,jpg,gif,bmp,png) file only</div>");

<% } %>