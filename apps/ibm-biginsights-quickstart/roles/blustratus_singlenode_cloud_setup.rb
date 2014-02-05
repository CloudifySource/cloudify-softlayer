name        'blustratus_singlenode_cloud_setup'
description 'BLU Stratus singlenode for cloud deployment'

run_list  'recipe[blustratus::create_and_activate_swap]',
          'recipe[blustratus::setup_ibm_java_7_jdk]',
          'role[db2]',
          'role[cognos_bi]',
          'recipe[blustratus::setup_web_console]',
          'recipe[ldap]',
          'recipe[blustratus::setup_ldap]',
          'recipe[blustratus::configure_db2]',
          'recipe[cognos_bi::configure_db2_content_store]',
          'recipe[cognos_bi::setup_env_for_cognos_samples]',
          'recipe[cognos_bi::configure_and_start]',
          'recipe[blustratus::configure_and_start_web_console]',
          'recipe[blustratus::callback]'

# Attributes applied if the node doesn't have it set already.
default_attributes  'cognos' => {
                      'install_fixpacks'  => 'YES'
                    },
                    'cloud' => {
                      'provider'          => 'aws'
                    },
                    'ldap' => {
                      'first'             => 'YES'
                    },
                    'imcloud' => {
                      'api' => {
                        'key' => '2f085845cae03d084f1f522ce1a0c9c49f4374f15da192783872bc23cff17ee4',
                        'url' => 'https://staging.imdemocloud.com/api'
                      }
                    }

# Attributes applied no matter what the node has set already.
override_attributes()
