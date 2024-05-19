# Controllers Summary

## CommentController
Handles operations related to comments on posts.

- **Base URL**: `/comments`
- **Dependencies**: `CommentRepository`, `PostRepository`

### Endpoints
1. **GET `/post/{postId}`**
   - Displays comments for a specific post.
   - Model attributes: `post`, `comments`.
   - Returns: `postDetails` template or redirects to `/forum` if post not found.

2. **POST `/create/{postId}`**
   - Creates a new comment for a specific post.
   - Model attributes: `currentUser`, `commentEntity`.
   - Redirects to `/post/{postId}` or `/forum` if post not found.

## PatchNoteController
Manages patch notes.

- **Dependencies**: `PatchNotesRepository`, `PatchService`

### Endpoints
1. **GET `/`**
   - Displays all patch notes.
   - Model attributes: `patches`, `patchNotesEntity`.
   - Returns: `home` template.

2. **POST `/createPatchNote`**
   - Creates a new patch note.
   - Model attributes: `currentUser`, `patchNotesEntity`.
   - Redirects to `/adminpage` or returns `error` in case of exceptions.

## PostController
Handles operations related to forum posts.

- **Base URL**: `/forum`
- **Dependencies**: `PostRepository`, `CommentRepository`, `PostService`

### Endpoints
1. **GET `/forum`**
   - Displays all forum posts.
   - Model attributes: `posts`, `postEntity`.
   - Returns: `forum` template.

2. **GET `/post/{postId}`**
   - Displays details of a specific post and its comments.
   - Model attributes: `post`, `comments`, `commentEntity`.
   - Returns: `postDetails` template or redirects to `/forum` if post not found.

3. **POST `/createPost`**
   - Creates a new post.
   - Model attributes: `currentUser`, `postEntity`.
   - Redirects to `/forum`.

4. **GET `/deletePost/{id}`**
   - Deletes a specific post.
   - Redirects to `/adminpage`.

## UserController
Manages user-related operations.

- **Dependencies**: `UserRepository`, `AppPasswordConfig`, `PostRepository`, `PatchNotesRepository`, `UserService`

### Endpoints
1. **GET `/users/edit/{id}`**
   - Displays the form to edit a user's details.
   - Model attributes: `user`, `roles`, `pageTitle`.
   - Returns: `updateUser` template or redirects to `/adminpage` if user not found.

2. **POST `/users/update`**
   - Updates user details.
   - Model attributes: `user`.
   - Redirects to `/login` or returns `error` in case of exceptions.

3. **GET `/deleteUser/{id}`**
   - Deletes a specific user.
   - Redirects to `/adminpage`.

4. **GET `/adminpage`**
   - Displays the admin page with user, post, and patch note lists.
   - Model attributes: `listUsers`, `posts`, `postEntity`, `patchNotesEntity`.
   - Returns: `adminpage` template.

5. **GET `/register`**
   - Displays the registration form.
   - Model attributes: `roles`.
   - Returns: `register` template.

6. **POST `/register`**
   - Registers a new user.
   - Model attributes: `userEntity`, `result`, `roles`.
   - Redirects to `/login` or returns `register` if validation errors occur.
