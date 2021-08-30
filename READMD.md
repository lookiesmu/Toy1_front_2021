# To Me의 Frontend 저장소 💌

[To Me 개발 노션 링크](https://www.notion.so/To-me-901869b392db44209a32f1d441759fd9)

# Data 를 어떻게 저장해야 할까요?
1. 우리는 핸드폰에 저장해야 할 모든 데이터를 구조화된 것으로는 **Room** 를 사용하고 
2. key value 의 형태로 저장해야 할 데이터는 __Jetpack Preferences__ 를 사용합니다.

# 네트워크 요청을 어떻게 해야 할까요?
1. kotlin coroutine 을 적극적으로 지원해주는 KTX 들을 통해서 **Ktor** 을 사용하여 요청합니다.
2. ![image](https://user-images.githubusercontent.com/79887707/131351821-65e80b75-6eff-4d43-94e1-38fa920c5c5f.png)
3. 받은 **JSON** 은 **GSON** 을 이용해서 직렬화 합니다.
4. ![image](https://user-images.githubusercontent.com/79887707/131351527-c6b618f7-098c-4e46-9a2a-d767e01170f2.png)
5. 위 사진 처럼 테스트 함수를 실행시켜 트랜잭션이 잘 일어나는지 함수별로 검즘한다.
6. ![image](https://user-images.githubusercontent.com/79887707/131351780-b19a0ace-95d6-4527-baec-1dd955adfcff.png)



# 라이브러리 총 정리
## [Android Jetpack](https://developer.android.com/jetpack/getting-started)
>1. [Navigation](https://developer.android.com/guide/navigation?hl=ko)
>2. [Room](https://developer.android.com/topic/libraries/architecture/room)
>3. [Preference](https://developer.android.com/guide/topics/ui/settings/use-saved-values)
## [Android KTX](https://developer.android.com/kotlin/ktx)
>1. [Activity KTX](https://developer.android.com/kotlin/ktx/extensions-list#androidxactivity)
>2. [Fragment KTX](https://developer.android.com/kotlin/ktx#fragment)
>3. [Lifecycle KTX](https://developer.android.com/kotlin/ktx#lifecycle)
>4. [LiveData KTX](https://developer.android.com/kotlin/ktx#livedata)
>5. [Room  KTX](https://developer.android.com/kotlin/ktx#room)
>6. [ViewModel KTX](https://developer.android.com/kotlin/ktx#viewmodel)
>7. [preference KTX](https://developer.android.com/kotlin/ktx/extensions-list#androidxpreference)
## [Ktor](https://ktor.io/docs/getting-started-ktor-client.html#create-client)
>1. [Ktor 전용 GSON](https://ktor.io/docs/json.html#jvm_dependency)

# [앱 아키텍처](https://developer.android.com/jetpack/guide)
<img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png"/>
