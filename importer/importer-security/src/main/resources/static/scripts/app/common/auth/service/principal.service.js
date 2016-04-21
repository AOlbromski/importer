angular.module('importerApp')
    .factory('Principal', function ($q, Account) {
        var principalIdentity,
            principalAuthenticated = false;

        return {
            isIdentityResolved: function () {
                return angular.isDefined(principalIdentity);
            },
            isAuthenticated: function () {
                return principalAuthenticated;
            },
            isInRole: function (role) {
                if (!principalAuthenticated || !principalIdentity || !principalIdentity.roles) {
                    return false;
                }

                return principalIdentity.roles.indexOf(role) !== -1;
            },
            isInAnyRole: function (roles) {
                if (!principalAuthenticated || !principalIdentity.roles) {
                    return false;
                }

                for (var i = 0; i < roles.length; i++) {
                    if (this.isInRole(roles[i])) {
                        return true;
                    }
                }

                return false;
            },
            authenticate: function (identity) {
                principalIdentity = identity;
                principalAuthenticated = identity !== null;
            },
            identity: function (force) {
                var deferred = $q.defer();

                if (force === true) {
                    principalIdentity = undefined;
                }

                // check and see if we have retrieved the identity data from the server.
                // if we have, reuse it by immediately resolving
                if (angular.isDefined(principalIdentity)) {
                    deferred.resolve(principalIdentity);

                    return deferred.promise;
                }

                // retrieve the identity data from the server, update the identity object, and then resolve.
                Account.get().$promise
                    .then(function (account) {
                        principalIdentity = account.data;
                        principalAuthenticated = true;
                        deferred.resolve(principalIdentity);
                    })
                    .catch(function () {
                        principalIdentity = null;
                        principalAuthenticated = false;
                        deferred.resolve(principalIdentity);
                    });
                return deferred.promise;
            }
        };
    });
