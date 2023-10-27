import cv2
import numpy as np
from matplotlib import pyplot as plt

face_img = cv2.imread("얼굴사진.png")
eye_img = cv2.imread("눈사진.png")

face_img = cv2.cvtColor(face_img, cv2.COLOR_BGR2RGB)
eye_img = cv2.cvtColor(eye_img, cv2.COLOR_BGR2RGB)

plt.imshow(face_img)
plt.show()
plt.imshow(eye_img)
plt.show()

face_gray = cv2.cvtColor(face_img, cv2.COLOR_BGR2GRAY)

eye_gray = cv2.cvtColor(eye_img, cv2.COLOR_BGR2GRAY)
eye_flip = cv2.flip(eye_gray, 1)


find_left = cv2.matchTemplate(face_gray, eye_gray, cv2.TM_CCOEFF_NORMED)
minVal1, maxVal1, minLoc1, maxLoc1 = cv2.minMaxLoc(find_left)
find_right = cv2.matchTemplate(face_gray, eye_flip, cv2.TM_CCOEFF_NORMED)
minVal2, maxVal2, minLoc2, maxLoc2 = cv2.minMaxLoc(find_right)

point_left = maxLoc1
point_right = maxLoc2

print("왼쪽 눈 위치: ", point_left)
print("오른쪽 눈 위치: ", point_right)

cv2.rectangle(face_img, point_left, (point_left[0] + eye_gray.shape[1], point_left[1] + eye_gray.shape[0]), (0xFF, 0, 0), 2)
cv2.rectangle(face_img, point_right, (point_right[0] + eye_gray.shape[1], point_right[1] + eye_gray.shape[0]), (0xFF, 0, 0), 2)
plt.imshow(face_img)
plt.show()

image_1 = cv2.imread("3.png")
image_2 = cv2.imread("4.png")
image_1 = cv2.cvtColor(image_1, cv2.COLOR_BGR2RGB)
image_2 = cv2.cvtColor(image_2, cv2.COLOR_BGR2RGB)
gray_1 = cv2.cvtColor(image_1, cv2.COLOR_BGR2GRAY)
gray_2 = cv2.cvtColor(image_2, cv2.COLOR_BGR2GRAY)

# SIFT 서술자 추출기 생성
detector = cv2.ORB_create()

# Keypoints와 descriptors 추출
keypoints1, desc1 = detector.detectAndCompute(image_1, None)
keypoints2, desc2 = detector.detectAndCompute(image_2, None)

# 매처 생성
matcher = cv2.BFMatcher(cv2.NORM_HAMMING, crossCheck=True)

# 매칭 계산
matches = matcher.match(desc1, desc2)

# 결과 출력
res = cv2.drawMatches(image_1, keypoints1, image_2, keypoints2, matches, None, flags=cv2.DRAW_MATCHES_FLAGS_NOT_DRAW_SINGLE_POINTS)
# res = cv2.drawMatches(image_1, keypoints1, image_2, keypoints2, matches[:50], None, flags=2)

# 결과 이미지 출력
plt.figure(figsize=(18, 18))
plt.imshow(res)
plt.show()

img = cv2.imread("얼굴사진.png")
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.imshow(img)
plt.show()

noise_img = img.copy()
noise_img = np.clip((img / 255 + np.random.normal(scale=0.1, size=img.shape)) * 255, 0, 255).astype('uint8')
# write your code
filtered_img = cv2.GaussianBlur(noise_img, (5, 5), 0)
#

plt.figure(figsize=(12,12))
plt.subplot(1,3,1)
plt.title('original image')
plt.imshow(img)
plt.subplot(1,3,2)
plt.title('image with noise')
plt.imshow(noise_img)
plt.subplot(1,3,3)
plt.title('filtered image')
plt.imshow(filtered_img)
plt.show()

np.random.seed(42)
noise_img2 = img.copy()
N = 100000
x = np.random.randint(img.shape[0], size=N)
y = np.random.randint(img.shape[1], size=N)
noise_img2[x, y] = 0
# write your code
kernal_size = 7
filtered_img2 = cv2.medianBlur(noise_img2, kernal_size)
#

plt.figure(figsize=(12,12))
plt.subplot(1,3,1)
plt.title('original image')
plt.imshow(img)
plt.subplot(1,3,2)
plt.title('image with noise')
plt.imshow(noise_img2)
plt.subplot(1,3,3)
plt.title('filtered image')
plt.imshow(filtered_img2)
plt.show()


img = cv2.imread("얼굴사진.png")
img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

# write your code
sobel_img = cv2.magnitude(cv2.Sobel(img, cv2.CV_64F, 1, 0), cv2.Sobel(img, cv2.CV_64F, 0, 1))
#

plt.imshow(sobel_img, cmap='gray')
plt.show()


img = cv2.imread("feature_extracting.jpeg")
gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
gray_img = np.float32(gray_img)

# Harris corner detection
distension = cv2.cornerHarris(gray_img, blockSize=2, ksize=3, k=0.04)

# 결과를 원본 이미지에 표시
img[distension > 0.01 * distension.max()] = [0, 0, 255]

plt.imshow(img)
plt.show()

# 이미지 파일 로드
imgL = cv2.imread('DM_left_image.png', cv2.IMREAD_GRAYSCALE)
imgR = cv2.imread('DM_right_image.png', cv2.IMREAD_GRAYSCALE)

# StereoSGBM 매개변수 설정
stereo = cv2.StereoSGBM_create(
    numDisparities=16,  # 깊이 범위 (64의 배수)
    blockSize=15,        # 매칭 블록 크기 (홀수)
)

# 깊이 맵 계산
disparity_map = stereo.compute(imgL, imgR)

# 깊이 맵을 8비트 이미지로 변환
disparity_map = cv2.convertScaleAbs(disparity_map, alpha=255.0 / 256.0)

# 깊이 맵 출력
plt.imshow(disparity_map, cmap='gray')
plt.show()



