# Model Summary

## CommentEntity
Represents a comment on a post.

- **Annotations**: `@Entity`

### Fields
- `Long id`
  - Primary key, auto-generated.
- `String content`
  - Content of the comment.
- `LocalDateTime createdAt`
  - Timestamp of when the comment was created.
- `UserEntity createdBy`
  - The user who created the comment.
- `PostEntity post`
  - The post associated with the comment.

### Constructors
- **CommentEntity()**
  - Default constructor.
- **CommentEntity(String content, LocalDateTime createdAt, UserEntity createdBy, PostEntity post)**
  - Parameterized constructor to initialize all fields.

### Methods
- Getters and setters for all fields.

## CommentRepository
Repository interface for `CommentEntity`.

- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<CommentEntity, Long>`

### Custom Methods
- `List<CommentEntity> findByPost(PostEntity post)`
  - Finds comments by the associated post.

## PatchNotesEntity
Represents a patch note entry.

- **Annotations**: `@Entity`

### Fields
- `Long id`
  - Primary key, auto-generated.
- `LocalDateTime createdAt`
  - Timestamp of when the patch note was created.
- `String version`
  - Version information of the patch.
- `String body`
  - Body content of the patch note.
- `String patchType`
  - Type of the patch.
- `UserEntity createdBy`
  - The user who created the patch note.

### Constructors
- **PatchNotesEntity()**
  - Default constructor.
- **PatchNotesEntity(LocalDateTime createdAt, String version, String body, String patchType, UserEntity createdBy)**
  - Parameterized constructor to initialize all fields.

### Methods
- Getters and setters for all fields.

## PatchNotesRepository
Repository interface for `PatchNotesEntity`.

- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<PatchNotesEntity, Long>`

## PostEntity
Represents a post created by a user.

- **Annotations**: `@Entity`

### Fields
- `Long id`
  - Primary key, auto-generated.
- `String title`
  - Title of the post.
- `String language`
  - Language of the post.
- `String body`
  - Body content of the post.
- `LocalDateTime createdAt`
  - Timestamp of when the post was created.
- `UserEntity createdBy`
  - The user who created the post.
- `List<CommentEntity> comments`
  - List of comments associated with the post.

### Constructors
- **PostEntity()**
  - Default constructor.
- **PostEntity(String title, String language, String body, LocalDateTime createdAt, UserEntity createdBy)**
  - Parameterized constructor to initialize all fields.

### Methods
- Getters and setters for all fields.

## PostRepository
Repository interface for `PostEntity`.

- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<PostEntity, Long>`

## Roles
Enumeration of user roles with associated permissions.

### Fields
- `String permissions`
  - Permissions associated with the role.

### Methods
- `String getPermissions()`
  - Returns the permissions associated with the role.
- `List<GrantedAuthority> splitPermissions()`
  - Splits and returns permissions as a list of `GrantedAuthority`.
- `List<GrantedAuthority> getAuthorities()`
  - Returns a list of authorities including the role and permissions.

## UserEntity
Represents a user in the system.

- **Annotations**: `@Entity`, `@Table(name = "users")`
- **Implements**: `UserDetails`

### Fields
- `long id`
  - Primary key, auto-generated.
- `String username`
  - Username of the user.
- `String password`
  - Password of the user.
- `Roles role`
  - Role of the user.
- `boolean accountNonExpired`
  - Indicates if the account is expired.
- `boolean accountNonLocked`
  - Indicates if the account is locked.
- `boolean accountEnabled`
  - Indicates if the account is enabled.
- `boolean credentialsNonExpired`
  - Indicates if the credentials are expired.
- `List<PostEntity> posts`
  - List of posts created by the user.
- `List<CommentEntity> comments`
  - List of comments created by the user.
- `List<PatchNotesEntity> patchNotes`
  - List of patch notes created by the user.

### Constructors
- **UserEntity()**
  - Default constructor.
- **UserEntity(String username, String password, Roles roles, boolean accountNonExpired, boolean accountNonLocked, boolean accountEnabled, boolean credentialsNonExpired)**
  - Parameterized constructor to initialize all fields.

### Methods
- Getters and setters for all fields.
- **UserDetails** interface methods: `getAuthorities()`, `getPassword()`, `getUsername()`, `isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`, `isEnabled()`

## UserNotFoundException
Custom exception for user not found scenarios.

### Constructor
- **UserNotFoundException(String message)**
  - Initializes the exception with a message.

## UserRepository
Repository interface for `UserEntity`.

- **Annotations**: `@Repository`
- **Extends**: `JpaRepository<UserEntity, Long>`

### Custom Methods
- `UserEntity findByUsername(String username)`
  - Finds a user by username.
- `Optional<UserEntity> findById(long id)`
  - Finds a user by ID.
