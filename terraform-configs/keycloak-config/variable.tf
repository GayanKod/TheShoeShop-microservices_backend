variable "key_vault_id" {
  default = "/subscriptions/f32e14d5-8830-47fc-ac4e-8170c7cb845e/resourceGroups/99x/providers/Microsoft.KeyVault/vaults/sample-keycloak"
}

variable "azure_resource_group" {
  default = "99x"
}

variable "client_id_auth" {
  default = "admin-cli"
}

variable "client_id" {
  default = "spring-cloud-client"
}

variable "client_display_name" {
  default = "Spring-cloud-client"
}

variable "realm_id" {
  default = "the-shoe-shop-realm"
}

variable "realm_display_name" {
  default = "The-shoe-shop-realm"
}

variable "server_url" {
  default = "http://keycloak.demo.com:9191/"
}
