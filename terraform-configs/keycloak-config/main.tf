resource "keycloak_realm" "realm" {
  realm                                = "the-shoe-shop-realm"
  display_name                         = "The-shoe-shop-realm"
  enabled                              = true
  access_code_lifespan                 = "30m"
  sso_session_idle_timeout             = "30m"
  sso_session_max_lifespan             = "10h"
  offline_session_idle_timeout         = "720h"
  offline_session_max_lifespan_enabled = false
  registration_allowed                 = false
  registration_email_as_username       = false
  reset_password_allowed               = false
  verify_email                         = false
  login_with_email_allowed             = true
  account_theme                        = "keycloak"
  admin_theme                          = "keycloak"
  email_theme                          = "keycloak"
  login_theme                          = "keycloak"


  internationalization {
    supported_locales = [
      "de",
    ]
    default_locale = "de"
  }
  password_policy = "upperCase(1) and length(8) and notUsername(undefined)"

  smtp_server {
    from     = "your email"
    host     = "your smtp host"
    ssl      = false
    starttls = false
    auth {
      username = "your username"
      password = "your password"
    }
  }
}

resource "keycloak_openid_client" "service" {
  client_id									= var.client_id
  name  									= var.client_display_name
  realm_id   								= keycloak_realm.realm.id
  access_type								= "CONFIDENTIAL"
  access_token_lifespan						= 600
  direct_access_grants_enabled				= false
  enabled  									= true
  standard_flow_enabled						= false
  service_accounts_enabled					= true
  exclude_session_state_from_auth_response	= false
}
