<H1>Trade App</H1>


There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

![img_1.png](img_1.png)

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.

<hr/>

<h3>H2 Console</h3>
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:tradedb

<h3>Swagger</h3>
http://localhost:8080/swagger-ui.html

<h3>Sample Requests:</h3>
<pre>
{
"tradeId": "T1",
"version": "1",
"counterPartyId": "CP-1",
"bookId": "B1",
"maturityDate": "20/07/2022"
}
</pre>

<pre>
{
"tradeId": "T2",
"version": "1",
"counterPartyId": "CP-2",
"bookId": "B1",
"maturityDate": "20/07/2023"
}
</pre>

<pre>
{
"tradeId": "T2",
"version": "1",
"counterPartyId": "CP-1",
"bookId": "B1",
"maturityDate": "20/05/2021"
}
</pre>
