# SA-Assignment-01
The development of a Point of Sale (POS) system for a bookstore introduces a comprehensive solution designed to streamline operations and enhance customer experience. This system encompasses four primary services, each tailored to address specific needs within the bookstore environment.

1.	Book Repository Service: This service facilitates functionalities for book management operations as adding, updating, and deleting book records, as well as searching for books by various criteria like title, author, and publisher. It also provides a user-friendly interface for accessing above mentioned facilities without needing to have knowledge about the inside functionality of the service.
2.	Order Processing Service: This component handles the processing of customer orders, displaying and printing order details for both the customer and the bookstore staff. It ensures that orders are accurately recorded and managed.
3.	Payment Processing Service: This service is designed to validate payment details and manage transactions through various methods, including cash and credit cards. It plays a critical role in securing transactions, protecting both the bookstore's financial data and the customer's payment information. 
4.	Customer Request Service: This service allows customers to submit requests for books, providing a platform for customers to voice their interests or needs. It facilitates direct communication between customers and the bookstore, enabling the bookstore to better understand customer preferences and tailor its offerings accordingly. 

| Service                 | Producer/Provider                                 | Subscriber/Consumer                     | Author |
|--------------------------|---------------------------------------------------|----------------------------------------|--------|
| Book Repository Service | Book Service Provider, SQLite JDBC                | Book Service Consumer                 | IT21118340 |
| Order Processing Service | Order Processing Service Provider                | Order Processing Service               |        |
| Payment Processing Service | Payment Processing Service Provider              | Payment Processing Service             |        |
| Customer Request Service | Customer Request Service Provider                | Customer Request Service               |        |
