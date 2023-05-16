# Start Hub
Hub is the central point in the Selenium Grid that routes the JSON test commands to the nodes. It receives test requests from the client and routes them to the required nodes. To set up the Selenium Hub, open the command prompt, and navigate to the directory where the Selenium Server Standalone jar file is stored

`java -jar lib/selenium-server-standalone-3.9.0.jar -role hub`

 This will start the Hub automatically using port 4444 by default.

# Start Nodes
To start nodes open the command prompt and navigate to the directory, where the Selenium Server Standalone jar file is stored.

`java -Dwebdriver.chrome.driver=driver/chromedriver -jar lib/selenium-server-standalone-3.9.0.jar -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome, maxInstances=1"`