# Boots Boutique API Interface

## Tech Stack

 - Spring Boot
 - HTML, CSS
 - Javascript
 - RabbitMQ (Topic Exchange)

Here are what you can do:
- Increase or decrease the quantity of a boot
- Delete the boot
- Search boots with these parameters
  - Size (Float, equal)
  - Quantity (Integer, greater than)
  - Type (BootType, equal), 
  - Material (String, equal)

You can reach the interface with this URL: http://localhost:4001/index.html 


<img src="https://user-images.githubusercontent.com/21241545/131887528-87857f0f-ef0e-41ad-b8c5-2b8135c092a7.png" width="250">


### RabbitMQ Queues:

There are 2 queues in the RabbitMQ. One of them is for boot creation and the other one is boot deletion.

<img src="https://user-images.githubusercontent.com/21241545/131887528-87857f0f-ef0e-41ad-b8c5-2b8135c092a7.png" width="250">


##### Note: Frontend side is taken from a template.


