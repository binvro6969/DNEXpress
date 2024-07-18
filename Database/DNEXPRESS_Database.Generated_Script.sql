USE DN_EXPRESS
go
-- Insert data into Role table
INSERT INTO Role (Role_value) VALUES ('Customer'), ('Driver'), ('Staff'), ('Manager');
GO

-- Insert data into Driver_Type table
INSERT INTO Driver_Type (driver_type_value) VALUES ('Part_time'), ('Full_time'),('Truck');
GO

-- Insert data into Post_Office table
INSERT INTO Post_Office (Apartment_number, Street_name, District, Ward, City)
VALUES ('2765+GPV', N'Nguyễn Cơ Thạch', N'Hoà Hải', N'Ngũ Hành Sơn', N'Đà Nẵng'),
       ('362', N'Khâm Thiên', N'Thổ Quan', N'Đống Đa', N'Hà Nội'),
	   ('111', N'Phạm Văn Chiêu', N'Phường 14', N'Gò Vấp', N'Hồ Chí Minh'),
	   ('601',N'Nguyễn Bỉnh Khiêm', N'Đằng Hải', N'Hải An', N'Hải Phòng');
GO

-- Insert data into Account table
INSERT INTO Account (Lname, Fname, Email, Phone, Gender, Password, CCCD_number, Avatar, Dob, Role_id, Status)
VALUES 
('Nguyen', 'Nghia', 'nghia@example.com', '0123456789', 'M', 'password', '123456789', 'avatar.png', '1990-01-01', 1, 1), -- Customer
('Tran', 'Binh', 'binh@example.com', '0192837465', 'M', 'password', '111111111', 'avatar3.png', '1985-03-03', 3, 1), -- Manager
('Pham', 'Cuong', 'cuong@example.com', '0918273645', 'M', 'password', '222222222', 'avatar4.png', '1988-04-04', 4, 1), -- Staff
('Le', 'Anh', 'anh@example.com', '0987654321', 'F', 'password', '987654321', 'avatar2.png', '1992-02-02', 2, 1), -- Driver
('Nguyen', 'Minh', 'minh@example.com', '0987654322', 'M', 'password', '987654322', 'avatar3.png', '1992-02-03', 2, 1),-- Driver
('Tran', 'Hai', 'hai@example.com', '0987654323', 'M', 'password', '987654323', 'avatar4.png', '1992-02-04', 2, 1),-- Driver
('Hoang', 'Bao', 'bao@example.com', '0987654324', 'F', 'password', '987654324', 'avatar5.png', '1992-02-05', 2, 1),-- Driver
('Pham', 'Linh', 'linh@example.com', '0987654325', 'F', 'password', '987654325', 'avatar6.png', '1992-02-06', 2, 1),-- Driver
('Vu', 'Thao', 'thao@example.com', '0987654326', 'F', 'password', '987654326', 'avatar7.png', '1992-02-07', 2, 1),-- Driver
('Nguyen', 'Hung', 'hung@example.com', '0987654327', 'M', 'password', '987654327', 'avatar8.png', '1992-02-08', 2, 1), -- Driver
('Tran', 'Khanh', 'khanh@example.com', '0987654328', 'M', 'password', '987654328', 'avatar9.png', '1992-02-09', 2, 1), -- Driver
('Le', 'Diep', 'diep@example.com', '0987654329', 'F', 'password', '987654329', 'avatar10.png', '1992-02-10', 2, 1), -- Driver
('Pham', 'Nhi', 'nhi@example.com', '0987654330', 'F', 'password', '987654330', 'avatar11.png', '1992-02-11', 2, 1), -- Driver
('Hoang', 'Nam', 'nam@example.com', '0987654331', 'M', 'password', '987654331', 'avatar12.png', '1992-02-12', 2, 1), -- Driver
('Vu', 'Tuan', 'tuan@example.com', '0987654332', 'M', 'password', '987654332', 'avatar13.png', '1992-02-13', 2, 1), -- Driver
('Do', 'Lan', 'lan@example.com', '0987654333', 'F', 'password', '987654333', 'avatar14.png', '1992-02-14', 2, 1), -- Driver
('Bui', 'Hoa', 'hoa@example.com', '0987654334', 'F', 'password', '987654334', 'avatar15.png', '1992-02-15', 2, 1), -- Driver
('Pham', 'Thuy', 'thuy@example.com', '0987654335', 'F', 'password', '987654335', 'avatar16.png', '1992-02-16', 2, 1), -- Driver
('Nguyen', 'Long', 'long@example.com', '0987654336', 'M', 'password', '987654336', 'avatar17.png', '1992-02-17', 2, 1), -- Driver
('Tran', 'Trung', 'trung@example.com', '0987654337', 'M', 'password', '987654337', 'avatar18.png', '1992-02-18', 2, 1), -- Driver
('Le', 'Quang', 'quang@example.com', '0987654338', 'M', 'password', '987654338', 'avatar19.png', '1992-02-19', 2, 1), -- Driver
('Pham', 'Phuong', 'phuong@example.com', '0987654339', 'F', 'password', '987654339', 'avatar20.png', '1992-02-20', 2, 1), -- Driver
('Hoang', 'Lan', 'lanh@example.com', '0987654340', 'F', 'password', '987654340', 'avatar21.png', '1992-02-21', 2, 1), -- Driver
('Vu', 'Hieu', 'hieu@example.com', '0987654341', 'M', 'password', '987654341', 'avatar22.png', '1992-02-22', 2, 1), -- Driver
('Do', 'Ngoc', 'ngoc@example.com', '0987654342', 'F', 'password', '987654342', 'avatar23.png', '1992-02-23', 2, 1), -- Driver
('Bui', 'Trang', 'trang@example.com', '0987654343', 'F', 'password', '987654343', 'avatar24.png', '1992-02-24', 2, 1), -- Driver
('Nguyen', 'Tam', 'tam@example.com', '0987654344', 'M', 'password', '987654344', 'avatar25.png', '1992-02-25', 2, 1), -- Driver
('Tran', 'Tung', 'tung@example.com', '0987654345', 'M', 'password', '987654345', 'avatar26.png', '1992-02-26', 2, 1), -- Driver
('Le', 'Thao', 'thaole@example.com', '0987654346', 'F', 'password', '987654346', 'avatar27.png', '1992-02-27', 2, 1); -- Driver
GO

-- Insert data into Customer table
INSERT INTO Customer (Customer_ID) VALUES (1); -- Assuming the first account is a customer
GO

-- Insert data into Driver table
INSERT INTO Driver (Driver_ID, License_number, driver_type_id,Rating, Online_Status,Post_Office_ID,Latitude,Longitude,IsWorking)
VALUES (4, 'B1234565', 1, 4.3, 1, 1, 16.006025, 108.259481,0),
	(5, 'B1234566', 1, 3.0, 1, 1, 16.000887, 108.259932,0),
	(6, 'B1234567', 2, 2.9, 1, 1, 16.001630, 108.246993,NULL),
	(7, 'B1234568', 2, 2.3, 1, 1, 15.977726, 108.255049,NULL),
	(8, 'B1234569', 1, 2.6, 1, 2, 21.025685, 105.822952,1),
	(9, 'B1234560', 2, 2.0, 1, 2, 21.026807, 105.848787,NULL),
	(10, 'B1234570', 2, 3.0, 1, 2, 21.042228, 105.825527,NULL),
	(11, 'B1234571', 1, 2.6, 1, 2, 21.044464, 105.754922,1),
	(12, 'B1234572', 1, 2.3, 1, 3, 10.853160, 106.631977,1),
	(13, 'B1234573', 2, 2.5, 1, 3, 10.889894, 106.610935,NULL),
	(14, 'B1234574', 2, 3.4, 1, 3, 10.859737, 106.606625,NULL),
	(15, 'B1234575', 1, 4.2, 1, 3, 10.836954, 106.619423,1),
	(16, 'B1234576', 1, 3.7, 1, 4, 20.853480, 106.682100,1),
	(17, 'B1234577', 2, 1.2, 1, 4, 20.848513, 106.678641,NULL),
	(18, 'B1234578', 2, 1.1, 1, 4, 20.852449, 106.676799,NULL),
	(19, 'B1234579', 1, 2.7, 1, 4, 20.854544, 106.674084,1),
	(20, 'B1234580', 1, 3.5, 1, 1, 15.986375, 108.240962,1),
	(21, 'B1234581', 1, 2.8, 1, 2, 20.983922, 105.841243,1),
	(22, 'B1234582', 3, NULL, 1, 1, NULL, NULL,NULL),
	(23, 'B1234583', 3, NULL, 1, 2, NULL, NULL,NULL),
	(24, 'B1234584', 3, NULL, 1, 3, NULL, NULL,NULL),
	(25, 'B1234585', 3, NULL, 1, 4, NULL, NULL,NULL),
	(26,'B12345832',1,4.2,1,1,15.996838, 108.235458,0);
GO

-- Insert data into Manager table
INSERT INTO Manager (Manager_ID, Post_Office_ID) VALUES (3, 2); -- Manager with first post office
GO

-- Insert data into Staff table
INSERT INTO Staff (Staff_ID, Post_Office_ID) VALUES (2, 2); -- Staff with second post office
GO

--- Insert data into Shipment_Order table
INSERT INTO Shipment_Order (
    Customer_ID,  Created_Date, Order_Date, Cust_Feedback, Cust_Driver_Rating, 
    Pick_Up_Apartment_Number, Pick_Up_Street_Name, Pick_Up_District, Pick_Up_Ward, Pick_Up_City,
    Final_Receiver_Name, Final_Receiver_Phone, 
    Final_Apartment_Number, Final_Street_Name, Final_District, Final_Ward, Final_City,
    In_Province
)
VALUES 
(1,  '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'Good service', 1, '579', N'Lê Văn Hiến', N'Hoà Hải', N'Ngũ Hành sơn', N'Đà Nẵng', 'John Doe', '1234567890', '358', N'Võ Nguyên Giáp', N'Bắc Mỹ An', N'Ngũ Hành sơn', N'Đà Nẵng', 1),
(1,  '2023-01-02 00:00:00', '2023-01-02 00:00:00', 'Excellent', 5, '02', N'Ấp Bắc', N'Hoà Hải', N'Ngũ Hành sơn', N'Đà Nẵng', 'Jane Smith', '0987654321', 'K16', N'Mỹ Đa Đông 12', N'Bắc Mỹ An', N'Ngũ Hành sơn', N'Đà Nẵng', 1),
(1, '2023-02-01 00:00:00', '2023-02-01 00:00:00', 'Good service', 5, '20', N'Huỳnh Văn Nghệ', N'Hoà Hải', N'Ngũ Hành sơn', N'Đà Nẵng', 'Michael Brown', '1122334455', '01', N'Phan Tứ', N'Bắc Mỹ An', N'Ngũ Hành sơn', N'Đà Nẵng', 1),
(1, '2023-03-01 00:00:00', '2023-03-01 00:00:00', 'Good service', 5, '208', N'Nguyễn Trãi', N'Văn Quán', N'Hà Đông', N'Hà Nội', 'Emily Davis', '2233445566', '90', N'Nguyễn Tuân', N'Thanh Xuân Trung', N'Thanh Xuân', N'Hà Nội', 1),
(1, '2024-06-15 00:00:00', '2024-06-15 00:00:00', 'Good service', 4, '30', N'Trần Đại Nghĩa', N'Hoà Hải', N'Ngũ Hành sơn', N'Đà Nẵng', 'Sophia Wright', '1122334455', '89', N'P.Hạ Lũng', N'Đằng Hải', N'Hải An', N'Hải Phòng', 0),
(1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Not satisfied', 2, '100', N'Trần Đại Nghĩa', N'Hoà Hải', N'Ngũ Hành sơn', N'Đà Nẵng', 'Ryan Robinson', '2233445566', '18', N'Nguyễn Chí Thanh', N'Ngọc Khánh', N'Ba Đình', N'Hà Nội', 0),
(1, '2024-07-15 00:00:00', '2024-07-15 00:00:00', 'Driver was polite', 4, '450', N'Chợ Hàng 8', N'Dư Hàng Kênh', N'Lê Chân', N'Hải Phòng', 'Ava Walker', '3344556677', '345', N'Trần Khát Chân', N'Thanh Nhàn', N'Hai Bà Trưng', N'Hà Nội', 0);                                                                                                                     
go

-- Insert data into Shipment_Order_Driver table
INSERT INTO Shipment_Order_Driver (Shipment_Order_ID, Driver_ID)
VALUES 
(1, 4),
(2, 5),
(3, 6),
(4, 8),
(5, 20),
(6, 26),
(7, 16)
GO



-- Insert data into Order_Status table
-- Insert data into Order_Status table
INSERT INTO Order_Status (Order_ID, Location, Location_Type, Status, Start_Time, End_Time, Post_Office_ID, Process,Shipment_Driver_ID)
VALUES 
-- Order ID 1
(1, N'Đà Nẵng', 'Sender', 'Picking up', '2023-01-01 09:00:00', '2023-01-01 09:00:00', NULL, 'Done',1),
(1, N'Đà Nẵng', 'Receiver', 'Delivering', '2023-01-01 18:00:00', '2023-01-01 18:00:00', NULL, 'Done',1),
(1, N'Hà Nội', 'Receiver', 'Delivered', '2023-01-01 18:00:00', '2023-01-01 18:00:00', NULL, 'Done',1),

-- Order ID 2
(2, N'Đà Nẵng', 'Sender', 'Picking up', '2023-01-02 09:00:00', '2023-01-02 09:00:00', NULL, 'Done',2),
(2, N'Đà Nẵng', 'Receiver', 'Delivering', '2023-01-02 18:00:00', '2023-01-02 18:00:00', NULL, 'Done',2),
(2, N'Đà Nẵng', 'Receiver', 'Delivered', '2023-01-02 18:00:00', '2023-01-02 18:00:00', NULL, 'Done',2),

-- Order ID 3
(3, N'Đà Nẵng', 'Sender', 'Picking up', '2023-02-01 09:00:00', '2023-02-01 09:00:00', NULL, 'Not yet',NULL),
(3, N'Đà Nẵng', 'Receiver', 'Delivering', '2023-02-01 18:00:00', '2023-02-01 18:00:00', NULL, 'Not yet',NULL),
(3, N'Đà Nẵng', 'Receiver', 'Delivered', '2023-02-01 18:00:00', '2023-02-01 18:00:00', NULL, 'Not yet',NULL),

-- Order ID 4
(4, N'Hà Nội', 'Sender', 'Picking up', '2023-03-01 09:00:00', '2023-03-01 09:00:00', NULL, 'Ongoing',4),
(4, N'Hà Nội', 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet',NULL),
(4, N'Hà Nội', 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet',NULL),

-- Order ID 5
(5, N'Đà Nẵng', 'Sender', 'Picking up', NULL, NULL, NULL, 'Not yet',NULL),
(5, N'Đà Nẵng', 'Post Office', 'In Transit', NULL, NULL, 1, 'Not yet',NULL),
(5, N'Đà Nẵng', 'Post Office', 'At warehouse', NULL, NULL, 1, 'Not yet',NULL),
(5, N'Hải Phòng', 'Post Office', 'In Transit-2', NULL, NULL, 4, 'Not yet',NULL),
(5, N'Hải Phòng', 'Post Office', 'At warehouse-2', NULL, NULL, 4, 'Not yet',NULL),
(5, N'Hải Phòng', 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet',NULL),
(5, N'Hải Phòng', 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet',NULL),

-- Order ID 6
(6, N'Đà Nẵng', 'Sender', 'Picking up', NULL, NULL, NULL, 'Not yet',NULL),
(6, N'Đà Nẵng', 'Post Office', 'In Transit', NULL, NULL, 1, 'Not yet',NULL),
(6, N'Đà Nẵng', 'Post Office', 'At warehouse', NULL, NULL, 1, 'Not yet',NULL),
(6, N'Hà Nội', 'Post Office', 'In Transit-2', NULL, NULL, 2, 'Not yet',NULL),
(6, N'Hà Nội', 'Post Office', 'At warehouse-2', NULL, NULL, 2, 'Not yet',NULL),
(6, N'Hà Nội', 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet',NULL),
(6, N'Hà Nội', 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet',NULL),

-- Order ID 7
(7, N'Hải Phòng', 'Sender', 'Picking up', NULL, NULL, NULL, 'Not yet',NULL),
(7, N'Hải Phòng', 'Post Office', 'In Transit', NULL, NULL, 4, 'Not yet',NULL),
(7, N'Hải Phòng', 'Post Office', 'At warehouse', NULL, NULL, 4, 'Not yet',NULL),
(7, N'Hà Nội', 'Post Office', 'In Transit-2', NULL, NULL, 2, 'Not yet',NULL),
(7, N'Hà Nội', 'Post Office', 'At warehouse-2', NULL, NULL, 2, 'Not yet',NULL),
(7, N'Hà Nội', 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet',NULL),
(7, N'Hà Nội', 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet',NULL);




GO
-- Insert data into Package_Type table
INSERT INTO Package_Type (Package_Type_Value, Package_Type_Price) VALUES 
('Food', 5000),
('Clothing', 2000),
('Electronic', 7000),
('Fragile', 10000),
('Other', 5000);

GO

-- Insert data into Package_Size table
INSERT INTO Package_Size (Package_Size_Value, Package_Size_Price) VALUES ('S', 2000), ('M', 5000), ('L', 10000), ('XL', 20000);
GO

-- Insert data into Delivery_Type table
INSERT INTO Delivery_Type (Delivery_Type_Value, Delivery_Type_Price) VALUES ('Saver', 5000), ('Normal', 10000), ('Fast', 15000), ('On Specific Time', 20000);
GO


INSERT INTO Service_Type (Service_Type_Value) VALUES ('Warranty');
GO

INSERT INTO Service_Property (Service_Property_Value, Service_Property_Price, Service_Type_ID) VALUES  ('Basic', 0.1, 1), ('Standard', 0.25, 1), ('Advanced', 0.5, 1);
GO

-- Insert data into Package_Details table
INSERT INTO Package_Details (Order_ID, Package_Type_ID, Package_Size_ID, Delivery_Type_ID, Package_Note, Requested_Delivery_Date, Package_Weight, Package_Value, Requested_Receiving_Date, Package_Receiving_Note)
VALUES 
(1, 1, 1, 3, 'Fragile', '2023-01-01 00:00:00', 2.5, 12000.0, NULL, NULL),
(2, 2, 2, 3, 'Handle with care', '2023-01-02 00:00:00', 3.0, 12000.0, NULL, NULL),
(3, 1, 1, 3, 'Keep upright', '2023-02-01 00:00:00', 2.5, 12000.0, NULL, NULL),
(4, 2, 2, 3, 'Do not stack', '2023-03-01 00:00:00', 3.0, 12000.0, NULL, NULL),
(5, 1, 1, 3, 'Perishable', '2023-04-01 00:00:00', 2.5, 12000.0, NULL, NULL),
(6, 2, 2, 3, 'Keep dry', '2024-01-01 00:00:00', 3.0, 12000.0, NULL, NULL),
(7, 1, 1, 3, 'Temperature sensitive', '2024-03-01 00:00:00', 2.5, 12000.0, NULL, NULL)

GO
INSERT INTO Package_Service (Package_Details_ID, Service_Property_ID) VALUES
(1, 1), 
(2, 2), 
(3, 3), 
(4, 3), 
(5, 1), 
(6, 2),
(7, 3) 
GO

-- Insert data into Payment_Method table
INSERT INTO Payment_Method (Payment_Method_Value) VALUES ('Cash'), ('Online transaction');
GO

-- Insert data into Invoice table
INSERT INTO Invoice (Order_ID, Payment_Method_ID, Service_Fee, Total_Amount) 
VALUES 
(1, 1, 50.0, 150.0),
(2, 2, 25.0, 100.0),
(3, 1, 50.0, 200.0),
(4, 2, 30.0, 120.0),
(5, 1, 40.0, 180.0),
(6, 2, 35.0, 130.0),
(7, 1, 55.0, 210.0)
GO

INSERT INTO Vehicle_Type (Vehicle_Type_Value) 
VALUES    
    ('Motorcycle'),
    ('Truck'),
    ('Van');
GO

INSERT INTO Vehicle (Driver_ID, Vehicle_Type_ID, Plate_Number, Brand, Vehicle_Img) 
VALUES 
    (4, 1, 'ABC123', 'Yamaha', 'toyota_sedan.jpg'),
    (5, 2, 'DEF456', 'Ford', 'ford_suv.jpg'),
    (6, 3, 'GHI789', 'Chevrolet', 'chevrolet_truck.jpg'),
    (7, 1, 'JKL012', 'Honda', 'honda_motorcycle.jpg'),
    (8, 2, 'MNO345', 'Mercedes', 'mercedes_van.jpg'),
	(9, 3, 'MNO345', 'Mercedes', 'mercedes_van.jpg');
GO

INSERT INTO Ticket_Status (Ticket_Status_Value)
VALUES ('Processing'),
       ('Rejected'),
       ('Approved'),
       ('Done');
GO



GO
CREATE OR ALTER TRIGGER trg_UpdateDriverRating
ON Shipment_Order
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @OrderID INT;

    -- Get the Order_ID affected by the insert or update
    SELECT @OrderID = Order_ID FROM inserted;

    -- Update the Driver table's Rating based on average Cust_Driver_Rating
    UPDATE Driver
    SET Rating = (
        SELECT AVG(Cust_Driver_Rating)
        FROM Shipment_Order_Driver sod
        JOIN Shipment_Order so ON sod.Shipment_Order_ID = so.Order_ID
        WHERE sod.Driver_ID = Driver.Driver_ID
    )
    WHERE Driver_ID IN (
        SELECT Driver_ID 
        FROM Shipment_Order_Driver 
        WHERE Shipment_Order_ID = @OrderID
    );
END;


GO
CREATE TRIGGER trg_InsertOrderStatus
ON Shipment_Order
AFTER INSERT
AS
BEGIN
    DECLARE @Order_ID INT;
    DECLARE @In_Province BIT;
    DECLARE @Pick_Up_Location NVARCHAR(500);
    DECLARE @Final_Location NVARCHAR(500);
    DECLARE @Created_Date DATETIME;
    DECLARE @Pick_Up_Post_Office_ID INT;
    DECLARE @Final_Post_Office_ID INT;

    -- Get the inserted Order_ID, In_Province values, Pick-Up location details, and Order Date
    SELECT 
        @Order_ID = Order_ID, 
        @In_Province = In_Province,
        @Pick_Up_Location = Pick_Up_City,
        @Final_Location = Final_City,
        @Created_Date = Created_Date
    FROM inserted;

    -- Get the Post_Office_ID for the pick-up location
    SELECT @Pick_Up_Post_Office_ID = Post_Office_ID 
    FROM Post_Office 
    WHERE City = @Pick_Up_Location;

    -- Get the Post_Office_ID for the final location
    SELECT @Final_Post_Office_ID = Post_Office_ID 
    FROM Post_Office 
    WHERE City = @Final_Location;

    IF @In_Province = 0
    BEGIN
        -- Insert 4 rows into Order_Status table
        INSERT INTO Order_Status (Order_ID, Location, Location_Type, Status, Start_Time, End_Time, Post_Office_ID, Process)
        VALUES
        (@Order_ID, @Pick_Up_Location, 'Sender', 'Picking up', NULL, NULL, NULL, 'Not yet'),
        (@Order_ID, @Pick_Up_Location, 'Post Office', 'In Transit', NULL, NULL, @Pick_Up_Post_Office_ID, 'Not yet'),
		(@Order_ID, @Pick_Up_Location, 'Post Office', 'At warehouse', NULL, NULL, @Pick_Up_Post_Office_ID, 'Not yet'),
		(@Order_ID, @Final_Location, 'Post Office', 'In Transit-2',  NULL, NULL, @Final_Post_Office_ID, 'Not yet'),
        (@Order_ID, @Final_Location, 'Post Office', 'At warehouse-2',  NULL, NULL, @Final_Post_Office_ID, 'Not yet'),
        (@Order_ID, @Final_Location, 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet'),
		(@Order_ID, @Final_Location, 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet');
    END
    ELSE
    BEGIN
        -- Insert 2 rows into Order_Status table
        INSERT INTO Order_Status (Order_ID, Location, Location_Type, Status, Start_Time, End_Time, Post_Office_ID, Process)
        VALUES
        (@Order_ID, @Pick_Up_Location, 'Sender', 'Picking up', NULL, NULL, NULL, 'Not yet'),
	    (@Order_ID, @Final_Location, 'Receiver', 'Delivering', NULL, NULL, NULL, 'Not yet'),
        (@Order_ID, @Final_Location, 'Receiver', 'Delivered', NULL, NULL, NULL, 'Not yet');
    END
END;

  
  
