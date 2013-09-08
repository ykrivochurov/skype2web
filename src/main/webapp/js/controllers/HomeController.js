function HomeController($scope, ChatService, FeedItemService) {
  $scope.selectedDate = new Date().getTime();
  ChatService.crud.loadAll({}, function (chats) {
    $scope.chats = chats;
  });

  $scope.loadFeedItemsByChat = function (chat) {
    $scope.selectedChat = chat;
    ChatService.crud.loadDatesById({id: chat.id}, function (chatDates) {
      $scope.chatDates = chatDates;
    });
    FeedItemService.crud.loadByChat({chatId: chat.id}, {date: new Date().getTime()}, function (feedItems) {
      $scope.feedItems = feedItems;
      if ($scope.ws != null) {
        $scope.ws.close();
      }
      $scope.ws = new WebSocket("ws://" + location.hostname + (location.port ? ':' + location.port : '') + "/skype2web/websocket/");

      $scope.ws.onopen = function () {
        console.log('WebSocket opened');
        $scope.ws.send($scope.selectedChat.id);
      };

      $scope.ws.onmessage = function (message) {
        try {
          var feedItem = JSON.parse(message.data);
          //is same date
          var feedItemDate = new Date(feedItem.creationDate);
          var currentDate = new Date($scope.selectedDate);
          if (feedItemDate.getYear() === currentDate.getYear()
            && feedItemDate.getMonth() === currentDate.getMonth()
            && feedItemDate.getDay() === currentDate.getDay()) {
            $scope.feedItems.unshift(feedItem);
          }
        } catch (e) {
          console.log("Unable to parse socket message: " + message);
        }
      };
    });
  };

  $scope.loadFeedItemsByDate = function (chatDate) {
    $scope.selectedDate = chatDate.date;
    FeedItemService.crud.loadByChat({chatId: $scope.selectedChat.id}, {date: chatDate.date}, function (feedItems) {
      $scope.feedItems = feedItems;
    });
  }
}
