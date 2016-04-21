angular.module('importerApp')
    .service('ImportService', function ($http) {
        var baseUrl = 'api/import';
        return {
            getAll: function () {
                return $http.get(baseUrl).then(getData);
            },
            create: function (itemsList) {
                return $http.put(baseUrl, itemsList
                ).then(getData);
            },
            delete: function (id) {
                return $http.delete(baseUrl, {
                    params: {
                        id: id
                    }
                }).then(getData);
            }
        };
        function getData(response) {
            return response.data;
        }
    });
