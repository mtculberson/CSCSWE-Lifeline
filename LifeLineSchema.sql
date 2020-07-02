DROP TABLE IF EXISTS dbo.[emergency_contact]
DROP TABLE IF EXISTS dbo.[emergency]
DROP TABLE IF EXISTS dbo.[users]
DROP TABLE IF EXISTS dbo.[contact]
DROP TABLE IF EXISTS dbo.[video]
DROP TABLE IF EXISTS dbo.[location]

CREATE TABLE dbo.[video](
    [VIDEO_ID] uniqueidentifier NOT NULL
        CONSTRAINT PK_video_VIDEO_ID PRIMARY KEY
        CONSTRAINT DF_video_VIDEO_ID DEFAULT newsequentialid(),
    [VIDEO_DATA] varbinary(max) NULL,
    [CREATED_ON] datetime NOT NULL
        CONSTRAINT DF_video_CREATED_ON DEFAULT getdate()
)

CREATE TABLE dbo.[location] (
    [LOCATION_ID] uniqueidentifier NOT NULL
        CONSTRAINT PK_location_LOCATION_ID PRIMARY KEY
        CONSTRAINT DF_location_LOCATION_ID DEFAULT newsequentialid(),
    [LONGITUDE] decimal(11,8) NOT NULL,
    [LATITUDE] decimal(10,8) NOT NULL
)

CREATE TABLE dbo.[contact] (
   [CONTACT_ID] uniqueidentifier NOT NULL
       CONSTRAINT PK_contact_CONTACT_ID PRIMARY KEY
       CONSTRAINT DF_contact_CONTACT_ID DEFAULT newsequentialid(),
   [FIRST_NAME] nvarchar(255) NOT NULL,
   [LAST_NAME] nvarchar(255) NOT NULL,
   [PHONE_NUMBER] nvarchar(20) NOT NULL,
   [CREATED_ON] datetime NOT NULL
       CONSTRAINT DF_contact_CREATED_ON DEFAULT getdate(),
   [UPDATED_ON] datetime NULL
)


CREATE TABLE dbo.[users] (
     [USER_ID] uniqueidentifier NOT NULL
         CONSTRAINT PK_users_USER_ID PRIMARY KEY
         CONSTRAINT DF_users_USER_ID DEFAULT newsequentialid(),
     [USERNAME] nvarchar(max) NOT NULL,
     [ENABLED] bit DEFAULT 1,
     [PASSWORD] nvarchar(max) NULL,
     [ROLE] nvarchar(255) NOT NULL,
     [CONTACT_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_users_CONTACT_ID_contact_CONTACT_ID REFERENCES dbo.contact(CONTACT_ID)
)

CREATE TABLE dbo.[emergency] (
     [EMERGENCY_ID] uniqueidentifier NOT NULL
         CONSTRAINT PK_emergency_EMERGENCY_ID PRIMARY KEY
         CONSTRAINT DF_emergency_EMERGENCY_ID DEFAULT newsequentialid(),
     [USER_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_emergency_USER_ID_user_USER_ID REFERENCES dbo.users([USER_ID]),
     [VIDEO_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_emergency_VIDEO_ID_user_USER_ID REFERENCES dbo.[video]([VIDEO_ID]),
     [LOCATION_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_emergency_LOCATION_ID_location_LOCATION_ID REFERENCES dbo.[location](LOCATION_ID),
     [CREATED_ON] datetime NOT NULL
         CONSTRAINT DF_emergency_CREATED_ON DEFAULT getdate()
)

CREATE TABLE dbo.[emergency_contact] (
     [EMERGENCY_CONTACT_ID] uniqueidentifier NOT NULL
         CONSTRAINT PK_emergency_contact_EMERGENCY_CONTACT_ID PRIMARY KEY
         CONSTRAINT DF_emergency_contact_EMERGENCY_CONTACT_ID DEFAULT newsequentialid(),
     [USER_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_emergency_contact_USER_ID_user_USER_ID REFERENCES dbo.users([USER_ID]),
     [CONTACT_ID] uniqueidentifier NOT NULL
         CONSTRAINT FK_emergency_contact_CONTACT_ID_contact_CONTACT_ID REFERENCES dbo.contact(CONTACT_ID)
)
