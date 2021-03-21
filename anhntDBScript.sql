USE [master]
GO
/****** Object:  Database [AnhNTDatabase]    Script Date: 3/21/2021 1:00:16 AM ******/
CREATE DATABASE [AnhNTDatabase]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AnhNTDatabase', FILENAME = N'E:\Microsoft SQL Server\MSSQL15.SQLEXPRESS2019\MSSQL\DATA\AnhNTDatabase.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AnhNTDatabase_log', FILENAME = N'E:\Microsoft SQL Server\MSSQL15.SQLEXPRESS2019\MSSQL\DATA\AnhNTDatabase_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [AnhNTDatabase] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AnhNTDatabase].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AnhNTDatabase] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET ARITHABORT OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AnhNTDatabase] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AnhNTDatabase] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AnhNTDatabase] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AnhNTDatabase] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [AnhNTDatabase] SET  MULTI_USER 
GO
ALTER DATABASE [AnhNTDatabase] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AnhNTDatabase] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AnhNTDatabase] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AnhNTDatabase] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AnhNTDatabase] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AnhNTDatabase] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [AnhNTDatabase] SET QUERY_STORE = OFF
GO
USE [AnhNTDatabase]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 3/21/2021 1:00:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderId] [int] NOT NULL,
	[custName] [nvarchar](255) NOT NULL,
	[custAddress] [varchar](255) NOT NULL,
	[date] [date] NULL,
 CONSTRAINT [PK__Orders__0809335D3EF15330] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdersDetail]    Script Date: 3/21/2021 1:00:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdersDetail](
	[orderId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	[orderQuantity] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/21/2021 1:00:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productId] [int] NOT NULL,
	[storeQuantity] [int] NOT NULL,
	[tags] [int] NOT NULL,
	[productDesc] [nvarchar](60) NULL,
 CONSTRAINT [PK__Product__2D10D16AC351F946] PRIMARY KEY CLUSTERED 
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 3/21/2021 1:00:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[userId] [varchar](255) NOT NULL,
	[password] [varchar](255) NULL,
	[fullname] [nvarchar](255) NULL,
	[isAdmin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 3/21/2021 1:00:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tag](
	[tagId] [int] NOT NULL,
	[description] [varchar](255) NOT NULL,
 CONSTRAINT [PK__Tag__50FC015701A3B53B] PRIMARY KEY CLUSTERED 
(
	[tagId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Orders] ([orderId], [custName], [custAddress], [date]) VALUES (1, N'anh  ka ka', N'SE1503', CAST(N'2021-03-20' AS Date))
GO
INSERT [dbo].[Orders] ([orderId], [custName], [custAddress], [date]) VALUES (2, N'LMAO', N'123', CAST(N'2021-03-20' AS Date))
GO
INSERT [dbo].[Orders] ([orderId], [custName], [custAddress], [date]) VALUES (3, N'khanh dai ca', N'SE1503', CAST(N'2021-03-21' AS Date))
GO
INSERT [dbo].[Orders] ([orderId], [custName], [custAddress], [date]) VALUES (4, N'edcba', N'12R', CAST(N'2021-03-21' AS Date))
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (1, 1, 1)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (1, 3, 1)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (1, 6, 1)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (2, 1, 2)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (2, 5, 1)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (2, 6, 1)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (3, 1, 4)
GO
INSERT [dbo].[OrdersDetail] ([orderId], [productId], [orderQuantity]) VALUES (4, 1, 1)
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (1, 151, 1, N'Prepare for the SCWCD')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (2, 2, 0, N'A Mind For Numberss')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (3, 14, 4, N'Life of Chopin')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (4, 11, 5, N'Modern Art forms')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (5, 14, 3, N'Frankenstein')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (6, 14, 3, N'Call of the Wild')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (7, 11, 3, N'White Fang')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (8, 12, 0, N'Fur')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (9, 11, 3, N'Fur 2 ')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (10, 2, 4, N'Fur 3')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (11, 8, 1, N'Test')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (13, 1, 1, N'Test')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (14, 1, 4, N'Zelda')
GO
INSERT [dbo].[Product] ([productId], [storeQuantity], [tags], [productDesc]) VALUES (15, 12, 4, N'A Fox In Space')
GO
INSERT [dbo].[Registration] ([userId], [password], [fullname], [isAdmin]) VALUES (N'aaaaaa', N'1234567', N'anhnt', 1)
GO
INSERT [dbo].[Registration] ([userId], [password], [fullname], [isAdmin]) VALUES (N'abcdef', N'123456á', N'anhbcde', 1)
GO
INSERT [dbo].[Registration] ([userId], [password], [fullname], [isAdmin]) VALUES (N'eeeeee', N'123456', N'edcba', 0)
GO
INSERT [dbo].[Registration] ([userId], [password], [fullname], [isAdmin]) VALUES (N'khanhkk', N'12345', N'khanh dai ca', 0)
GO
INSERT [dbo].[Registration] ([userId], [password], [fullname], [isAdmin]) VALUES (N'luycôior54', N'123456', N'luycôio@', 0)
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (0, N'Self-helpp')
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (1, N'Programming')
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (3, N'Literature')
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (4, N'Music')
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (5, N'Arts')
GO
INSERT [dbo].[Tag] ([tagId], [description]) VALUES (7, N'Romance')
GO
ALTER TABLE [dbo].[OrdersDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrdersDet__order__4AB81AF0] FOREIGN KEY([orderId])
REFERENCES [dbo].[Orders] ([orderId])
GO
ALTER TABLE [dbo].[OrdersDetail] CHECK CONSTRAINT [FK__OrdersDet__order__4AB81AF0]
GO
ALTER TABLE [dbo].[OrdersDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrdersDet__produ__2D27B809] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
GO
ALTER TABLE [dbo].[OrdersDetail] CHECK CONSTRAINT [FK__OrdersDet__produ__2D27B809]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK__Product__tags__286302EC] FOREIGN KEY([tags])
REFERENCES [dbo].[Tag] ([tagId])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK__Product__tags__286302EC]
GO
USE [master]
GO
ALTER DATABASE [AnhNTDatabase] SET  READ_WRITE 
GO
