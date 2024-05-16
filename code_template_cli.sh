#!/bin/bash

echo "어떤 플랫폼인가요?"
echo "1. LeetCode"
echo "2. 백준 (BOJ)"
read -p "선택: " platform

case $platform in
  1) platform="LeetCode" ;;
  2) platform="BOJ" ;;
  *) echo "잘못된 입력입니다." && exit 1 ;;
esac

echo "어떤 언어로 작성하시겠습니까?"
echo "1. Java"
echo "2. Kotlin"
echo "3. Python"
read -p "선택: " language

case $language in
  1)
    extension="java"
    language_name="Java"
    ;;
  2)
    extension="kt"
    language_name="Kotlin"
    ;;
  3)
    extension="py"
    language_name="Python"
    ;;
  *)
    echo "잘못된 입력입니다."
    exit 1
    ;;
esac

echo "코드를 입력하세요 (끝내려면 빈 줄 또는 'done'을 입력하세요):"
code=""
while IFS= read -r line; do
  if [[ -z "$line" || "$line" == "done" ]]; then
    break
  fi
  code+="$line"$'\n'
done

if [ -z "$code" ]; then
  echo "작업이 취소되었습니다."
  exit 0
fi

read -p "문제 번호 (optional): " problem_number
read -p "문제 설명 (optional): " problem_description

current_date=$(date +%Y-%m-%d)
folder_name=$platform
file_name="${current_date}_${problem_number:-Unknown}.$extension"

mkdir -p "$folder_name"
echo "$code" > "$folder_name/$file_name"

branch_name="${current_date}_${platform}_${problem_number:-Unknown}_${problem_description:-NoDescription}"
git checkout -b "$branch_name"

git add "$folder_name/$file_name"

commit_message="Add solution for $platform problem #${problem_number:-Unknown} in $language_name"
git commit -m "$commit_message"

git push -u origin "$branch_name"

echo "파일 생성 후 브랜치에 커밋 및 푸시되었습니다."