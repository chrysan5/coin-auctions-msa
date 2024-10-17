# Bash 쉘 스크립트

# Acitions-compose 에서 빌드한 프로젝트에 대한 이미지를 찾기위한 postfix
services=(
"auction"
"auth"
"bidwin"
"chat"
"coinpay"
"gateway"
"server"
)

# 도커 이미지에 commit hash를 기반으로한 이미지 태그를 설정합니다.
commit_hash=$(git rev-parse --short HEAD)

# 빌드된 Docker 이미지가 있는지 확인
echo "=== 현재 Docker 이미지 목록 ==="
docker images

for service in "${services[@]}"
do
  imageName="$ECR_REGISTRY/$ECR_NAMESPACE/$service"

  # 이미지를 구분하기 위해서 latest 이외의 태그(커밋 해시태그)를 추가합니다.
  # (태그가 달라도 둘 다 동일한 이미지로 한 번만 저장, 두 개의 태그로 조회될 뿐)
  docker tag "$imageName:latest" "$imageName:$commit_hash"

  # AWS ECR에 Push
  docker push "$imageName:latest"
  docker push "$imageName:$commit_hash"

  echo "$service image is built and pushed to AWS ECR"
done

echo "ECR 푸시 완료 오예"