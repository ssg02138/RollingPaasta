#include <ESP8266WiFi.h>
 
const char* ssid = "shadow";
const char* password = "shadow15@!";

IPAddress staticIP(192,168,0,107);
IPAddress gateway(192,168,0,1);
IPAddress subnet(255,255,255,0);

WiFiServer server(8080);
 
// 변수 지정
int LED_pin = 16;
int turn_on = 0;
int turn_off = 1;
 
void setup() {
  Serial.begin(115200);

  WiFi.config(staticIP, gateway ,subnet);
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
 
  // Start the server
  server.begin();

  analogWrite(2, 150);
  analogWrite(12, 150);
  analogWrite(15, 150);
  analogWrite(16, 150);
}
 
void loop() {
  // Check if a client has connected
  Serial.println(WiFi.localIP());
  WiFiClient client = server.available();
  if (!client) {
    return;
  }
 
  // Wait until the client sends some data
  Serial.println("new client");
  /*while(!client.available()){
    delay(1);
  }*/
 
  // Read the first line of the request
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();
 
  // Match the request
  if (request.indexOf("/full") != -1)  {
    analogWrite(2, 150);
    analogWrite(12, 150);
    analogWrite(15, 150);
    analogWrite(16, 150);
  }
  if (request.indexOf("/half") != -1)  {
    analogWrite(2, 75);
    analogWrite(12, 75);
    analogWrite(15, 75);
    analogWrite(16, 75);
  }
  if (request.indexOf("/zero") != -1)  {
    analogWrite(2, 0);
    analogWrite(12, 0);
    analogWrite(15, 0);
    analogWrite(16, 0);
  }
 
  // Return the response
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); //  do not forget this one
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");
  client.println("<br><br>");
  client.println("</html>");
 
  delay(1);
  Serial.println("Client disonnected");
  Serial.println("");
 
}
