function HomeController($scope, ChatService, FeedItemService) {
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
    });
  }
}
