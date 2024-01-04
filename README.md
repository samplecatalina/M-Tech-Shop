# MTechShop: Full-Stack Angular E-Commerce Application
## Technical Digest
- Built a full-stack E-commerce application with Angular front-end and Spring Boot back-end.

- Developed responsive front-end using Angular with HTML/CSS/TypeScript, leveraging scalable Angular
Reactive Form API and payment processing Stripe API; coupling with the back-end by REST API.

- Engineered the back-end with Spring Boot; manipulating MySQL database by Spring Data JPA/Hibernate.
- Deployed security mechanism for service authorization with Okta OpenID Connect (OIDC) through JSON Web
Token (JWT) and protected routes with Angular route guard CanActivate and OktaAuthGuard.
- Implemented HTTPS security features by key and self-signed certificate generated by OpenSSL/Java keytool.
## Features Overview
**Quick Peek!** A demo of this project is available at https://youtu.be/pprUfjNgJL0

This application supports ...

- in-site search through the search box;
- products pagination, categorization, and detailed view;
- fully functioning shopping cart with session storage, so you will not lose your selected products even if you accidentally close your tab or browser;
- checkout form validation, pre-populated drop-down list, and auto-fill from shipping address to billing address;
- order tracking with **UUID** for all customers, regardless of sign-in status;
- VIP member special access through **protected route**: a single page of special discounts and benefits, only available to VIP custsomers;
- enabled multiple **environment configurations**: this app could be booted in pre-configured production or QA environment with `npm start -- --configuration=production` or `npm start -- --configuration=qa`;
- user management and authentication with **Okta API**: comply with **OAuth 2.0** industry standard, leverage authorization code flow with **Proof Key for Code Exchange (PKCE)** to control access between app and auth server, and manage sign-up users through Okta panel;
- secure communication with **HTTPS**: the communication from the web browser to the Angular app, from the Angular app to Spring Boot back-end, is secured by HTTPS with self-signed certificate generated by OpenSSL;
- secure payment process in compliance with **Payment Card Industry Data Security Standard** (PCI DSS) features with Stripe: debit/credit card payment will be securely handled directly by Stripe, and this web app will not have access to customer's sensitive information;
- automatic email receipt: upon successful payment, the customer will receive an email receipt sent from us.
