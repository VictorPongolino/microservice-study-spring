
Create a keystore named as secret and edit the configuration properties with the generated password;
```
keytool -genkeypair -alias secret -keyalg RSA -dname "CN=TheCommonName,OU=OrganizationUnitName,O=OrganizationName,L=LocationName,S=StateName,C=CountryName" -keypass <PASSWORD>
    -keystore secret.jks -storepass <PASSWORD>
```

The dname refers to Distinguished Name. It contains the server identity, called the Common Name (CN), as well as other relevant information about your Organizational Unit (OU), Oranization(O), Locality (L), State (S) and Country (C