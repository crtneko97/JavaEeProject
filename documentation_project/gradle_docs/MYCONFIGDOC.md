# Configuration Summary

## AppWebConfig
Configures view controllers and resource handling for the web application.

- **Annotations**: `@Configuration`, `@EnableWebMvc`

### Methods
1. **addViewControllers(ViewControllerRegistry registry)**
   - Maps URLs to specific view templates.
   - Mappings:
     - `/` -> `home`
     - `/forum` -> `forum`
     - `/about` -> `about`
     - `/login` -> `login`
     - `/logout` -> `logout`
     - `/register` -> `register`
     - `/adminpage` -> `adminpage`

2. **addResourceHandlers(ResourceHandlerRegistry registry)**
   - Configures handling of static resources (CSS, JS, images).
   - Resource handler: `/static/**`
   - Resource location: `classpath:/static/`

## AppSecurityConfig
Sets up security configurations including authentication, authorization, and security filters.

- **Annotations**: `@Configuration`, `@EnableWebSecurity`, `@EnableMethodSecurity`

### Dependencies
- `AppPasswordConfig`
- `UserService`

### Methods
1. **securityFilterChain(HttpSecurity http)**
   - Configures the security filter chain.
   - Disables CSRF protection.
   - Configures URL authorization:
     - Permits all: `/`, `/hash`, `/register`, `/api/user`
     - Requires `ROLE_ADMIN`: `/adminpage`
     - Requires `GET` authority: `/createPost`
   - Customizes form login:
     - Login page: `/login`
   - Configures remember-me functionality:
     - Token validity: 21 days
     - Remember-me parameter: `remember-me`
   - Configures logout:
     - Logout URL: `/perform_logout`
     - Clears authentication, invalidates session, deletes cookies.
     - Success URL: `/`
   - Sets authentication provider to `daoAuthenticationProvider`.

2. **daoAuthenticationProvider()**
   - Configures DAO authentication provider.
   - Uses BCrypt password encoder from `AppPasswordConfig`.
   - Sets user details service to `UserService`.

## AppPasswordConfig
Provides configuration for password encoding.

- **Annotations**: `@Configuration`

### Methods
1. **bCryptPasswordEncoder()**
   - Creates and returns a `BCryptPasswordEncoder` with strength 10.
