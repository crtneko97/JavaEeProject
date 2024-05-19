# Service Summary

## PatchService
Handles operations related to patch notes.

- **Annotations**: `@Service`

### Dependencies
- `PatchNotesRepository`

### Constructor
- **PatchService(PatchNotesRepository patchNotesRepository)**
  - Initializes `patchNotesRepository`.

## PostService
Manages post-related operations.

- **Annotations**: `@Service`

### Dependencies
- `PostRepository`

### Constructor
- **PostService(PostRepository postRepository)**
  - Initializes `postRepository`.

### Methods
1. **deletePost(long id)**
   - Deletes a post by its ID using `postRepository`.

## UserService
Provides user-related services including authentication and user management.

- **Annotations**: `@Service`, `UserDetailsService`

### Dependencies
- `UserRepository`

### Constructor
- **UserService(UserRepository userRepository)**
  - Initializes `userRepository`.

### Methods
1. **loadUserByUsername(String username)**
   - Loads user details by username for authentication.
   - Throws `UsernameNotFoundException` if the user is not found.

2. **getAllUsers()**
   - Retrieves a list of all users from the repository.

3. **getUserById(long id)**
   - Retrieves a user by their ID.
   - Throws `UserNotFoundException` if the user is not found.

4. **deleteUser(long id)**
   - Deletes a user by their ID using `userRepository`.

5. **saveUser(UserEntity user)**
   - Saves or updates a user in the repository.
   - If the user's ID is greater than 0, it updates the existing user.
   - If the user's ID is 0, it saves a new user.
   - Throws `UserNotFoundException` if the user is not found during an update.

