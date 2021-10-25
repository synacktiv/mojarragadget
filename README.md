# mojarragadget

## description

We found a new Java gadget chain in the Mojarra Library, this repo contains 2 POC to reproduce the exploit on version 2.3 and 3.0.

More information here:
- https://www.synacktiv.com/publications/finding-gadgets-like-its-2015-part-1.html
- https://www.synacktiv.com/publications/finding-gadgets-like-its-2015-part-2.html

## build

### version 2.3

```
cd mojaraaGadget-JSF-2.3/
mvn package
sudo docker build -t mojaraagadget-jsf-2.3 .
sudo docker run --rm -it -p 4848:4848 -p 8080:8080 mojaraagadget-jsf-2.3
```

Then the following URLs are exposed:

- http://127.0.0.1:8080/mojaraaGadget-JSF-2.3/exploit.xhtml
- http://127.0.0.1:8080/mojaraaGadget-JSF-2.3/readObject.xhtml


### version 3.0

```
cd mojaraaGadget-JSF-3.0/
mvn package
sudo docker build -t mojaraagadget-jsf-3.0 .
sudo docker run --rm -it -p 4848:4848 -p 8080:8080 mojaraagadget-jsf-3.0
```

You need to redeploy the app before using it due to some weird class loading problems. First wait for this message:

```
[#|2021-10-25T14:39:30.099+0000|INFO|Payara 5.2021.3|fish.payara.boot.runtime.BootCommand|_ThreadID=1;_ThreadName=main;_TimeMillis=1635172770099;_LevelValue=800;|
  Boot Command deploy returned with result SUCCESS : PlainTextActionReporterSUCCESSDescription: deploy AdminCommandApplication deployed with name mojaraaGadget-JSF-3.0.
    [name=mojaraaGadget-JSF-3.0
|#]
```

Then redeploy the app:

```
sudo docker ps
sudo docker exec -it -u root <CONTAINER ID> bash
asadmin --passwordfile passwordFile redeploy --name mojaraaGadget-JSF-3.0 deployments/mojaraaGadget-JSF-3.0.war
```

Then the following URLs are exposed:

- http://127.0.0.1:8080/mojaraaGadget-JSF-3.0/exploit.xhtml
- http://127.0.0.1:8080/mojaraaGadget-JSF-3.0/readObject.xhtml