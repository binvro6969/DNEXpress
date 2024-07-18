go
Create database DN_EXPRESS
go
use DN_EXPRESS
go
CREATE TABLE [Role] (
	Role_ID int Identity(1,1) PRIMARY KEY,
	Role_Value varchar(20) CONSTRAINT CHK_ROLE CHECK (Role_value In ('Customer','Driver','Manager','Staff')),
);
go
-- Creating Post_Office table
CREATE TABLE Post_Office (
    Post_Office_ID INT Identity(1,1) PRIMARY KEY,  
	Apartment_Number NVARCHAR(500),
    Street_Name NVARCHAR(500),
    District NVARCHAR(500) not null,
    Ward NVARCHAR(500) not null,
    City NVARCHAR(500) not null,
);
go
-- Creating Account table
CREATE TABLE Account (
    Account_ID INT Identity(1,1) PRIMARY KEY,
    Lname NVARCHAR(255),
    Fname NVARCHAR(255),
    Email NVARCHAR(255),
    Phone NVARCHAR(20),
    Gender CHAR(1) CONSTRAINT CHK_Account_Gender CHECK (Gender In ('M','F','O')),
    Password NVARCHAR(255),
    CCCD_number NVARCHAR(20),
    Avatar NVARCHAR(255),
	Dob Date,
	Role_ID int,
	Status bit,
	FOREIGN KEY (Role_ID) REFERENCES [Role] (Role_ID),
);

	
go
-- Creating Customer table
CREATE TABLE Customer (    
    Customer_ID INT  PRIMARY KEY,	
    FOREIGN KEY (Customer_ID) REFERENCES Account(Account_ID)
);


	
go
CREATE TABLE Driver_Type (
    Driver_Type_ID INT Identity(1,1) PRIMARY KEY,
    Driver_Type_Value NVARCHAR(255)
);

go
-- Creating Driver table
CREATE TABLE Driver (
    Driver_ID INT PRIMARY KEY, 
    License_number VARCHAR(50),    
	Driver_Type_ID INT,
	Online_Status bit,
	Rating Float,
	Latitude Float,
	Longitude Float,
	Post_Office_ID int,
	IsWorking bit,
	FOREIGN KEY (Post_Office_ID) REFERENCES Post_Office(Post_Office_ID),
    FOREIGN KEY (Driver_ID) REFERENCES Account(Account_ID),
	FOREIGN KEY (Driver_Type_ID) REFERENCES Driver_Type(Driver_Type_ID)
);



go
-- Creating Manager table
CREATE TABLE Manager (
    Manager_ID INT  PRIMARY KEY,
    Post_Office_ID INT,    
    FOREIGN KEY (Manager_ID) REFERENCES Account(Account_ID),
    FOREIGN KEY (Post_Office_ID) REFERENCES Post_Office(Post_Office_ID)
); 
go
-- Creating Staff table
CREATE TABLE Staff (
    Staff_ID INT  PRIMARY KEY,
    Post_Office_ID INT,    
    FOREIGN KEY (Staff_ID) REFERENCES Account(Account_ID),
    FOREIGN KEY (Post_Office_ID) REFERENCES Post_Office(Post_Office_ID)
);

go
-- Creating other related tables for complete schema
CREATE TABLE Shipment_Order (
    Order_ID INT Identity(1,1) PRIMARY KEY,
    Customer_ID INT,
	Created_Date DATETIME,
    Order_Date DATETIME,
    Cust_Feedback NVARCHAR(255),
	Cust_Driver_Rating float,
	Pick_Up_Apartment_Number NVARCHAR(500)not null,
    Pick_Up_Street_Name NVARCHAR(500) not null,
    Pick_Up_District NVARCHAR(500) not null,
    Pick_Up_Ward NVARCHAR(500) not null,
    Pick_Up_City NVARCHAR(500) not null,
	Final_Receiver_Name NVARCHAR(255),
	Final_Receiver_Phone NVARCHAR(20),
	Final_Apartment_Number VARCHAR(500)not null,
    Final_Street_Name NVARCHAR(500) not null,
    Final_District NVARCHAR(500) not null,
    Final_Ward NVARCHAR(500) not null,
    Final_City NVARCHAR(500) not null,
	In_Province BIT not null,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
);
go
-- Creating Shipment_Order_Driver tables
CREATE TABLE Shipment_Order_Driver (
	Shipment_Driver_ID INT Identity(1,1) PRIMARY KEY,
    Shipment_Order_ID INT,
    Driver_ID INT,	
    FOREIGN KEY (Shipment_Order_ID) REFERENCES Shipment_Order(Order_ID),
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID)
);


go
CREATE TABLE Order_Status(
	Order_Status_ID INT Identity(1,1) PRIMARY KEY,
	Order_ID INT,
	Location NVARCHAR(50),
    Location_Type VARCHAR(50) CONSTRAINT CHK_Location_Type CHECK (Location_Type IN ('Sender', 'Post Office','Receiver')),
	Status NVARCHAR(50)CONSTRAINT CHK_Status CHECK (Status IN ('Picking up', 'In transit','In transit-2','At warehouse-2', 'At warehouse', 'Delivering', 'Delivered', 'On date Failed')),
    Start_Time DATETIME,
    End_Time DATETIME,
    Post_Office_ID INT,
	Process NVARCHAR(50) CONSTRAINT CHK_Process CHECK (Process IN ('Ongoing', 'Not yet', 'Done', 'Failed')),
	Shipment_Driver_ID INT,
    FOREIGN KEY (Order_ID) REFERENCES Shipment_Order(Order_ID),
	FOREIGN KEY (Shipment_Driver_ID) REFERENCES Shipment_Order_Driver(Shipment_Driver_ID),
    FOREIGN KEY (Post_Office_ID) REFERENCES Post_Office(Post_Office_ID)
);

go
CREATE TABLE Issue_Ticket (
    Issue_Ticket_ID INT Identity(1,1) PRIMARY KEY,
    Customer_ID INT,
    Driver_ID INT,
	Staff_ID INT,
    Issue_Ticket_Value NVARCHAR(255),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID),
	FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID)
);
go
-- Creating Promotion table
CREATE TABLE Promotion (
    Promotion_ID INT Identity(1,1) PRIMARY KEY,
    Promotion_Value FLOAT,
    Promotion_Name NVARCHAR(255),
	Promotion_Description NVARCHAR(255)
);
go
-- Creating Payment_Method table
CREATE TABLE Payment_Method (
    Payment_Method_ID INT Identity(1,1) PRIMARY KEY,
    Payment_Method_Value NVARCHAR(255)
);
go
-- Creating Package_Type table
CREATE TABLE Package_Type (
    Package_Type_ID INT Identity(1,1) PRIMARY KEY,
    Package_Type_Value NVARCHAR(255),
	Package_Type_Price DECIMAL(10,2)
);
go
-- Creating Package_Size table
CREATE TABLE Package_Size (
    Package_Size_ID INT Identity(1,1) PRIMARY KEY,
    Package_Size_Value NVARCHAR(255),
	Package_Size_Price DECIMAL(10,2)
);

go
-- Creating Delivery_Type table
CREATE TABLE Delivery_Type (
    Delivery_Type_ID INT Identity(1,1) PRIMARY KEY,
    Delivery_Type_Value NVARCHAR(255),
	Delivery_Type_Price DECIMAL(10,2)
);

go
CREATE TABLE Service_Type (
    Service_Type_ID INT Identity(1,1) PRIMARY KEY,
    Service_Type_Value NVARCHAR(255)
);

go
CREATE TABLE Service_Property (
    Service_Property_ID INT Identity(1,1) PRIMARY KEY,
	Service_Property_Value NVARCHAR(255),
	Service_Property_Price DECIMAL(10,2),
	Service_Type_ID INT,

	FOREIGN KEY (Service_Type_ID) REFERENCES Service_Type (Service_Type_ID)
);



go
CREATE TABLE Package_Details (
    Order_ID INT PRIMARY KEY,
    Package_Type_ID INT,
    Package_Size_ID INT,    
	Delivery_Type_ID INT,
    Requested_Delivery_Date DATETIME,
    Package_Weight Float,
	Package_Value DECIMAL (10,2) not null,
	Package_Image VARCHAR(255),
    Package_Note NVARCHAR(255),
    Requested_Receiving_Date DATETIME,
    Package_Receiving_Note NVARCHAR(255),

	FOREIGN KEY (Order_ID) REFERENCES Shipment_Order(Order_ID),
    FOREIGN KEY (Package_Type_ID) REFERENCES Package_Type(Package_Type_ID),
    FOREIGN KEY (Package_Size_ID) REFERENCES Package_Size(Package_Size_ID),	
	FOREIGN KEY (Delivery_Type_ID) REFERENCES Delivery_Type(Delivery_Type_ID)
);


go
CREATE TABLE Package_Service (
    Package_Service_ID INT Identity(1,1) PRIMARY KEY,
    Package_Details_ID INT,
    Service_Property_ID INT,

    FOREIGN KEY (Service_Property_ID) REFERENCES Service_Property(Service_Property_ID),
    FOREIGN KEY (Package_Details_ID) REFERENCES Package_Details(Order_ID)
);

go
CREATE TABLE Invoice (	
    Order_ID INT PRIMARY KEY,
	Payment_Method_ID INT,
    Service_Fee FLOAT,
    Total_Amount FLOAT,
    FOREIGN KEY (Order_ID) REFERENCES Shipment_Order(Order_ID),
	FOREIGN KEY (Payment_Method_ID) REFERENCES Payment_Method(Payment_Method_ID)

);
go
-- Creating Promotion_Property table
CREATE TABLE Promotion_Property (
    Promotion_Property_ID INT Identity(1,1) PRIMARY KEY,
    Promotion_ID INT,
    Package_Details_ID INT,
    FOREIGN KEY (Promotion_ID) REFERENCES Promotion(Promotion_ID),
    FOREIGN KEY (Package_Details_ID) REFERENCES Shipment_Order(Order_ID)
);

go
CREATE TABLE Vehicle_Type (
    Vehicle_Type_ID INT Identity(1,1) PRIMARY KEY,
    Vehicle_Type_Value VARCHAR(255)
);
go
-- Vehicle and related tables
CREATE TABLE Vehicle (
    Vehicle_ID INT Identity(1,1) PRIMARY KEY,
    Driver_ID INT,
    Vehicle_Type_ID INT,    
    Plate_Number NVARCHAR(50),
    Brand NVARCHAR(50),
    Vehicle_Img NVARCHAR(255),
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID),
    FOREIGN KEY (Vehicle_Type_ID) REFERENCES Vehicle_Type(Vehicle_Type_ID)
);
go
-- Ticket_Status table
CREATE TABLE Ticket_Status (
    Ticket_Status_ID INT Identity(1,1) PRIMARY KEY,
    Ticket_Status_Value NVARCHAR(255)
);
go
-- Import_Export_Ticket table
CREATE TABLE Import_Export_Ticket (
    		Imp_Exp_Ticket_ID INT Identity(1,1) PRIMARY KEY,
    		Post_Office_ID INT,
    		Creator_ID INT,
    		Moderator_ID INT,
    		Package_Details_ID INT,
    		Ticket_Status_ID INT,
    		Create_Date DATE,
    		Consental_Date DATE,
    		integrity NVARCHAR(20),
    		labeling NVARCHAR(20),
    		quantity NVARCHAR(20),
    		documentation NVARCHAR(20),
			checkboxStatus NVARCHAR(255),
    		Note NVARCHAR(255),
		ticketType NVARCHAR(255),
    		FOREIGN KEY (Post_Office_ID) REFERENCES Post_Office(Post_Office_ID),
    		FOREIGN KEY (Creator_ID) REFERENCES Staff(Staff_ID),
    		FOREIGN KEY (Moderator_ID) REFERENCES  Manager(Manager_ID),
    		FOREIGN KEY (Package_Details_ID) REFERENCES Shipment_Order(Order_ID),
    		FOREIGN KEY (Ticket_Status_ID) REFERENCES Ticket_Status(Ticket_Status_ID)
	);
GO