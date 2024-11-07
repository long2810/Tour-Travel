# 계획
# Posting
블로그를 작성함으로써 자신의 경험을 다른 경험들과 공유할 수 있는 서비스를 제공합니다.
## 백앤드
### Service
1. [PostService](java/com/example/travel/post/posting/service/PostingService.java) (Create + Update + Delete 기능을 담당)
- `create()` 메소드를 통해서 블로그를 작성하여 PostingRepository에 저장했다
- `update` + `delete` 메소드를 사용해서 블로그를 수정하고 삭제할 수 있다.
2. [PostViewerSerive](com.example.travel.post.posting.service.PostingViewerService) (Read 기능을 담당)
- 하나의 블로그 안에서 블로그의 제목, 내용, 사진 , 댓글 등을 볼 수 있는 기능을 만들기 위해 `readOne` 메소드를 만들었다
- 모든 블로그를 볼 수 있는 기능을 실행하기 위해 `allPosting` 메소드를 만들었다
- 그리고 하나의 작성자가 쓴 블로그들을 리스트로 표시할 수 있는 기능도 `allPostingByWriter` 메소드를 통해서 확인할 수 있고 블로그를 키워드로 검색하기 위해 `allPostingKeyword` 메서드를 만들었다
3. [ImagePostingService](com.example.travel.post.posting.service.ImagePostingService)
- ImagePostingService는 게시물에 이미지를 업로드, 삭제, 조회하는 기능을 제공하는 서비스입니다.
- `uploadImgs`: 주어진 게시물 ID에 이미지를 업로드하고 URL을 생성해 데이터베이스에 저장합니다.
- `deleteImg`: 특정 이미지 ID를 통해 이미지를 삭제합니다.
- `listImgOnePost`: 특정 게시물 ID의 모든 이미지를 조회하여 리스트로 반환합니다.
4. [CommentService](com.example.travel.post.comment.service.CommentService)
- CommentService 클래스는 게시물의 댓글을 생성, 수정, 삭제, 조회하는 기능을 제공합니다. 주요 메서드는 다음과 같습니다:
- `create`: 로그인한 사용자가 특정 게시물에 댓글을 작성합니다.
- `update`: 댓글 작성자만 댓글을 수정할 수 있습니다.
- `delete`: 댓글 작성자만 해당 댓글을 삭제할 수 있습니다.
- `readOne`: 특정 댓글을 조회합니다.
- `listCommentPost`: 특정 게시물의 모든 댓글을 리스트로 반환합니다.
- `commentCountOfPost`: 특정 게시물의 댓글 개수를 반환합니다.
- `commentWriterImg`: 특정 댓글 작성자의 프로필 이미지를 반환합니다.
5. [LikeSerive](com.example.travel.post.emotion.LikeService)
- LikeService 클래스는 게시물에 대한 '좋아요' 기능을 관리합니다. 주요 메서드는 다음과 같습니다:
- `create`: 로그인한 사용자가 특정 게시물에 '좋아요'를 추가합니다. postingId를 통해 게시물을 찾고, 해당 게시물에 '좋아요'를 저장합니다.
- `existLike`: 로그인한 사용자가 특정 게시물에 '좋아요'를 이미 눌렀는지 확인합니다.
- `delete`: 특정 '좋아요' ID를 통해 '좋아요'를 삭제합니다.
6. [LocationService](com.example.travel.post.location.service.LocationService)
- LocationService 클래스는 게시물과 관련된 위치 정보를 관리합니다. 주요 메서드는 다음과 같습니다:
- `create`: 특정 게시물에 위치 정보를 추가합니다. postingId를 통해 게시물을 찾고, 해당 게시물에 위치 정보를 저장합니다.
- `delete`: 특정 위치 ID를 통해 위치 정보를 삭제합니다.
- `locationOfPost`: 특정 게시물에 연관된 모든 위치 정보를 조회하여 리스트로 반환합니다.
### Controller
1. [PostController](com.example.travel.post.posting.PostingViewerController)
- `RestMapping(posting)` 사용하여 페이지의 링크를 공정한다
- `POST /posting`: 새 게시물을 생성합니다. PostingDto를 요청 본문으로 받아 게시물을 저장하고, 생성된 게시물 정보를 반환합니다.
- `PUT /posting`/{postId}: 특정 게시물을 수정합니다. postId 경로 변수와 PostingDto 요청 본문을 통해 게시물을 업데이트합니다.
- `DELETE /posting`/{id}: 특정 ID에 해당하는 게시물을 삭제합니다.
- `GET /posting`: 로그인한 사용자가 작성한 모든 게시물의 리스트를 반환합니다.
2. [PostViewerController](com.example.travel.post.posting.PostingViewController) (PostingViewerController 클래스는 게시물을 조회하는 기능을 제공합니다)
    - `GET /posting-view` : 
        + `keyword`가 제공되면 `postingService.allPostingKeyword(keyword)`를 호출하여 키워드로 검색된 게시물을 반환합니다.
        + `userId`가 제공되면 `postingService.allPostingByWriter(userId)`를 호출하여 해당 사용자가 작성한 모든 게시물을 조회합니다.
        + 키워드나 사용자 ID가 없으면 `postingService.allPosting()`을 호출하여 모든 게시물을 반환합니다.
    - `GET /posting-view/{postId}` : `postingService.readOne(postId)`를 호출하여 postId에 해당하는 게시물을 반환합니다.
3. [ImagePostingController](com.example.travel.post.posting.ImageController)
- `POST /post-images/{postId}`
    + `imageService.uploadImgs(postId, image)`를 호출하여 이미지를 업로드합니다. 반환되는 ImagePostingDto 객체는 업로드된 이미지의 정보를 담고 있습니다.
- GET /post-images/{postId}
    + `imageService.listImgOnePost(postId)`를 호출하여 postId에 해당하는 게시물에 첨부된 모든 이미지 목록을 반환합니다.
4. [CommentController](com.example.travel.post.comment.CommentController)
- `POST /comments`: `commentService.create(dto)`를 호출하여 댓글을 생성하고, 생성된 댓글의 DTO를 반환합니다.
- `PUT /comments/{commentId}`: `commentService.update(dto, commentId)`를 호출하여 댓글을 수정하고, 수정된 댓글의 DTO를 반환합니다.
- `DELETE /comments/{commentId}`: `commentService.delete(commentId)`를 호출하여 댓글을 삭제합니다.
- `GET /comments/list/{postId}`: `commentService.listCommentPost(postId)`를 호출하여 게시물에 달린 댓글 목록을 반환합니다.
- `GET /comments/{commentId}`:` commentService.readOne(commentId)`를 호출하여 특정 댓글을 조회하고 반환합니다.
- `GET /comments/{postId}`: `commentService.commentCountOfPost(postId)`를 호출하여 해당 게시물에 달린 댓글 수를 반환합니다.
- `GET /comments/{commentId}/writerImg`: `commentService.commentWriterImg(commentId)`를 호출하여 댓글 작성자의 프로필 이미지를 반환합니다.

5. [LikeController](com.example.travel.post.emotion.LikeController)
- LikeController는 게시물에 대한 "좋아요" 기능을 처리하는 컨트롤러입니다. 주요 엔드포인트는 다음과 같습니다:
- `POST /like`: LikeDto에 담긴 데이터를 사용하여 게시물에 좋아요를 추가합니다. `likeService.create(dto)`를 호출하여 좋아요를 생성합니다.
- `GET /like`: 사용자가 해당 게시물을 이미 좋아요 했는지 확인합니다. `likeService.existLike(dto)`를 호출하여 좋아요 상태를 체크합니다.
- `DELETE /like/{id}`: 주어진 likeId로 좋아요를 삭제합니다. `likeService.delete(likeId)`를 호출하여 좋아요를 삭제합니다.
6. [LocationController](com.example.travel.post.location.LocationController)
- LocationController는 게시물의 위치 관련 기능을 처리하는 컨트롤러입니다. 주요 엔드포인트는 다음과 같습니다:
- `POST /location`: LocationDto에 담긴 데이터를 사용하여 게시물의 위치를 추가합니다. `locationService.create(dto)`를 호출하여 위치 정보를 생성합니다.
- `DELETE /location/{locationId}`: 주어진 locationId로 위치 정보를 삭제합니다. `locationService.delete(locationId)`를 호출하여 해당 위치를 삭제합니다.

7. [LocationViewerController](com.example.travel.post.location.LocationViewerController)
-` GET /location-view`: 요청 본문(LocationDto)에 포함된 게시물 ID를 사용하여 해당 게시물에 연결된 위치 정보를 조회합니다. `locationService.locationOfPost(dto)`를 호출하여 위치 정보를 가져옵니다. 이 API는 주로 게시물에 연결된 여러 위치 정보를 조회하는 데 사용됩니다.

## 프론트앤드
1. [home.html](resources/templates/home/home.html)
- 여행 코스: 서울에서 진행되는 다양한 여행 코스를 소개합니다.
- 블로그: 서울 여행과 관련된 블로그 포스트를 제공합니다.
- 관광지: 서울의 유명한 관광지 사진과 정보를 확인할 수 있습니다.
- 검색 기능: 사용자 검색을 통해 서울의 다양한 여행 정보 및 관광지를 찾아볼 수 있습니다.
2. [post-create.html](resources/templates/post/post-create.html)
- 블로그 제목과 내용 작성: 사용자는 블로그 글의 제목과 내용을 작성할 수 있습니다.
- 이미지 업로드: 사용자는 최대 3개의 이미지를 업로드할 수 있습니다.
- 페이지 분할: 첫 번째 단계(제목 및 내용 작성)와 두 번째 단계(이미지 업로드)로 나뉘어 있으며, 각 단계는 순차적으로 진행됩니다.
- 파일 제출: 각 이미지마다 제출 버튼이 제공되어 사진을 업로드할 수 있습니다.
- 반응형 디자인: 모바일과 데스크탑에서 모두 사용하기 편리한 반응형 디자인을 적용하였습니다.
3. [post.html](resources/templates/post/post.html)
- 블로그 제목 및 내용 보기: 블로그 포스트의 제목과 내용이 페이지에 표시됩니다.
- 프로필 이미지 및 작성자 정보 표시: 포스트 상단에 작성자의 이름과 프로필 이미지가 표시됩니다.
- 포스트 수정 기능: 작성자는 "수정" 버튼을 눌러 블로그 포스트의 제목과 내용을 수정할 수 있습니다.
- 댓글 작성 기능: 사용자는 댓글을 작성할 수 있으며, 댓글 입력란에는 블로그에 더 훈훈한 댓글을 남길 수 있는 메시지가 안내됩니다.
- 공감 버튼: 사용자는 공감 버튼을 클릭하여 블로그 포스트에 공감을 표현할 수 있습니다.
- 댓글 보기: 작성된 댓글을 확인할 수 있으며, 댓글 목록은 동적으로 표시됩니다.
4. [postList.html](resources/templates/post/postingList.html)
- 블로그 헤더: 배경 이미지와 함께 블로그 제목 및 게시판 이름을 표시합니다.
- 게시물 목록 보기: 블로그 게시물 목록을 동적으로 불러와 표시합니다.
- 게시물 생성: 사용자는 "Create Post" 버튼을 클릭하여 새로운 블로그 게시물을 작성할 수 있는 페이지로 이동합니다.
- 반응형 디자인: 페이지는 다양한 화면 크기에 맞게 조정됩니다.