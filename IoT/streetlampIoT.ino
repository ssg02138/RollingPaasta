#include <SoftwareSerial.h>

#include "ESP8266.h"

#define BT_RXD 2 
#define BT_TXD 3 
SoftwareSerial esp8266Serial(BT_RXD, BT_TXD); 

//SoftwareSerial esp8266Serial = SoftwareSerial(2, 3);
ESP8266 wifi = ESP8266(esp8266Serial);

void setup()
{
    Serial.begin(9600);

    // ESP8266
    // 통신 속도 맞춤 115200bps
    esp8266Serial.begin(115200);
    wifi.begin();
    wifi.setTimeout(1000);

    // getVersion
    char version[16] = {};
    Serial.print("getVersion: ");
    Serial.print(getStatus(wifi.getVersion(version, 16)));
    Serial.print(" : ");
    Serial.println(version);

    // RSSI 값을 뿌려주는 AP 모드로 설정
    Serial.print("setWifiMode: ");
    Serial.println(getStatus(wifi.setMode(ESP8266_WIFI_ACCESSPOINT)));

    // AP 설정값
    Serial.print("setAPConfiguration: ");
    Serial.println(getStatus(wifi.setAPConfiguration("NanBada2", "suwonuniversity", 10, ESP8266_ENCRYPTION_WPA_WPA2_PSK)));
    wifi.restart();

}

void loop()
{
    // getConnectedStations
    ESP8266Station stations[5];
    unsigned int stationCount;
    Serial.print("getConnectedStations: ");
    Serial.print(getStatus(wifi.getConnectedStations(stations, stationCount, 5)));
    Serial.print(" : ");
    Serial.println(stationCount);
    for (uint8_t i = 0; i < stationCount; i++) {
      Serial.print(" - ");
      Serial.print(stations[i].ip);
      Serial.print(" - ");
      for (uint8_t j = 0; j < 6; j++) {
        Serial.print(stations[i].mac[j], HEX);
        if (j < 5)
          Serial.print(":");
      }
      Serial.println();
    }
    delay(5000);

}

String getStatus(bool status)
{
    if (status)
        return "OK";

    return "KO";
}

String getStatus(ESP8266CommandStatus status)
{
    switch (status) {
    case ESP8266_COMMAND_INVALID:
        return "INVALID";
        break;

    case ESP8266_COMMAND_TIMEOUT:
        return "TIMEOUT";
        break;

    case ESP8266_COMMAND_OK:
        return "OK";
        break;

    case ESP8266_COMMAND_NO_CHANGE:
        return "NO CHANGE";
        break;

    case ESP8266_COMMAND_ERROR:
        return "ERROR";
        break;

    case ESP8266_COMMAND_NO_LINK:
        return "NO LINK";
        break;

    case ESP8266_COMMAND_TOO_LONG:
        return "TOO LONG";
        break;

    case ESP8266_COMMAND_FAIL:
        return "FAIL";
        break;

    default:
        return "UNKNOWN COMMAND STATUS";
        break;
    }
}

String getRole(ESP8266Role role)
{
    switch (role) {
    case ESP8266_ROLE_CLIENT:
        return "CLIENT";
        break;

    case ESP8266_ROLE_SERVER:
        return "SERVER";
        break;

    default:
        return "UNKNOWN ROLE";
        break;
    }
}

String getProtocol(ESP8266Protocol protocol)
{
    switch (protocol) {
    case ESP8266_PROTOCOL_TCP:
        return "TCP";
        break;

    case ESP8266_PROTOCOL_UDP:
        return "UDP";
        break;

    default:
        return "UNKNOWN PROTOCOL";
        break;
    }
}
