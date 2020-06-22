package com.learning.web;

//@RestController
//@RequestMapping("/auth")
//public class AuthResource {
//	private static Logger LOGGER = LogManager.getLogger("UserResource");
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	AuthenticationManager authenticationManager;
//	@Autowired
//	JwtUtils jwtUtils;
//
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
//		try {
//			Authentication authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			String jwt = jwtUtils.generateJwtToken(authentication);
//
//			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//					.collect(Collectors.toList());
//
//			return ResponseEntity.ok(userService.convertFromUserDetailsToDTO(userDetails, jwt));
//		} catch (Exception e) {
//			LOGGER.error("Problem occored in api/user" + ConstantBase.CRUD_REST_SAVE_OR_UPDATE + " : {} ", e);
//			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	
//}