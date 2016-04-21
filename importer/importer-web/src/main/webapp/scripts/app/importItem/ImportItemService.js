angular.module('importerApp')
    .service('ImportItemService', function ($http) {
        var baseUrl = 'api/item';
        return {
            getAll: function () {
                return $http.get(baseUrl).then(getData);
            },
            getPage: function (pageNumber, itemsNumber) {
                return $http.get(baseUrl + '/' + pageNumber + '/' + itemsNumber).then(getData);
            },
            getOne: function (id) {
                return $http.get(baseUrl + '/' + id).then(getData);
            },
            reidex: function (id) {
                return $http.get(baseUrl + '/index').then(indexRunMessage);
            },
            delete: function (id) {
                return $http.delete(baseUrl, {
                    params: {
                        id: id
                    }
                }).then(getData);
            },
            create: function (journalNamesDto) {
                return $http.put(baseUrl, journalNamesDto
                ).then(getData);
            },
            update: function (journalNamesDto) {
                return $http.post(baseUrl, journalNamesDto
                ).then(getData);
            }
        };
        function getData(response) {
            return response.data;
        }
        function indexRunMessage(response) {
            console.log(response);
        }
    });
