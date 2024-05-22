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

read -p "issue 번호: " issue_number

if [ -z "$issue_number" ]; then
  echo "issue 번호는 필수입니다."
  exit 1
fi

echo "코드를 입력하세요. 저장하고 에디터를 종료하면 스크립트가 계속됩니다."
tempfile=$(mktemp)
vim "$tempfile"
code=$(cat "$tempfile")
rm "$tempfile"

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

branch_name="solution/#${issue_number}"
git checkout -b "$branch_name"

git add "$folder_name/$file_name"

commit_message="[#${issue_number}] Add solution for $platform problem #${problem_number:-Unknown} in $language_name"
if [ -n "$problem_description" ]; then
  commit_message="$commit_message - $problem_description"
fi
git commit -m "$commit_message"

git push -u origin "$branch_name"

echo "파일 생성 후 브랜치에 커밋 및 푸시되었습니다."