package com.example.travel.tourPackage.createPackage;

import com.example.travel.tourPackage.entity.Package;
import com.example.travel.tourPackage.entity.Schedule;
import com.example.travel.tourPackage.repo.PackageRepo;
import com.example.travel.tourPackage.repo.ScheduleRepo;
import org.springframework.stereotype.Component;

@Component
public class PackageInfo {
    private final ScheduleRepo scheduleRepo;
    private final PackageRepo packageRepo;

    public PackageInfo(ScheduleRepo scheduleRepo, PackageRepo packageRepo) {
        this.scheduleRepo = scheduleRepo;
        this.packageRepo = packageRepo;
        this.package1();
        this.package2();
        this.package3();
    }

    public void package1(){
        Package tourPack1 = Package.builder()
                //경북궁,롯데월드 아쿠아리움,롯데월드타워 서울스카이,가나돈까스의집,가담,가락골마산아구찜,고고스,리버시티수상스키장,레전드히어로즈 잠실롯데월드몰점,강남스테이힐(Gangnam Stay Hill)
                .listTourists("126508,2476731,2492348,2871024,2869760,2757617,2685296,767780,2946635,2574118")
                .price(300000)
                .title("서울의 과거와 현재를 걷다: 전통과 현대의 1박 2일 여행")
                .build();
        packageRepo.save(tourPack1);
        Schedule tour1FirstDay = Schedule.builder()
                .tourPackage(tourPack1)
                .day("Day1")
                .mainActivity("능인선원 방문 \n 대모산 도시자연공원 트래킹 \n 롯데월드 아쿠아리움 탐방" +
                        "\n 롯데월드타워 서울스카이 전망대 방문")
                .detail("첫째 날: 역사와 현대 도시의 조화\n" +
                        "오전\n" +
                        "\n" +
                        "경복궁 방문 (09:00~11:00)\n" +
                        "서울의 대표적인 역사 유적지인 경복궁에서 조선 시대의 궁궐을 탐방하며 한국의 전통 건축과 문화에 대해 알아봅니다. 왕궁 수문장 교대식도 볼 수 있습니다.\n" +
                        "점심 2. 가나돈까스의집에서 점심식사 (12:00~13:00)\n" +
                        "\n" +
                        "강남에서 유명한 수제 돈까스 전문점에서 바삭한 돈까스를 맛보며 즐거운 식사 시간을 가지세요.\n" +
                        "오후 3. 롯데월드 아쿠아리움 탐방 (14:00~16:00)\n" +
                        "\n" +
                        "롯데월드 아쿠아리움에서 다양한 해양 생물을 구경하며 서울 도심에서 바다의 신비로움을 느껴보세요.\n" +
                        "\n" +
                        "롯데월드타워 서울스카이 전망대 방문 (16:30~18:00)\n" +
                        "서울에서 가장 높은 전망대인 서울스카이에서 360도 파노라마 뷰를 감상하며 도시의 아름다움을 만끽합니다. 특히 해 질 무렵의 노을을 감상하기 좋은 시간대입니다.\n" +
                        "저녁 5. 가락골마산아구찜에서 저녁식사 (19:00~20:30)\n" +
                        "\n" +
                        "매콤하고 시원한 아구찜으로 저녁을 즐기며 여행 첫날을 마무리합니다.\n" +
                        "숙박 6. Gangnam Stay Hill에서 숙박 (21:00~)\n" +
                        "\n" +
                        "강남에 위치한 편안한 숙소에서 편안히 휴식하며 하루를 마무리합니다.")
                .build();
        scheduleRepo.save(tour1FirstDay);
        Schedule tour1SecondDay = Schedule.builder()
                .tourPackage(tourPack1)
                .day("Day2")
                .mainActivity("리버시티수상스키장 체험 \n 레전드히어로즈 잠실 롯데월드몰점에서 실내 스포츠 게임 즐기기")
                .detail("아침\n" +
                        "\n" +
                        "Gangnam Stay Hill에서 체크아웃 및 간단한 아침 (08:00~09:00)\n" +
                        "오전 2. 리버시티수상스키장 체험 (09:30~11:00)\n" +
                        "\n" +
                        "잠실 근처 한강에서 수상스키나 웨이크보드를 체험하며 아드레날린을 충전합니다.\n" +
                        "점심 3. 가담에서 점심식사 (12:00~13:30)\n" +
                        "\n" +
                        "퓨전 한식을 제공하는 가담에서 맛있는 식사를 즐기세요.\n" +
                        "오후 4. 레전드히어로즈 잠실 롯데월드몰점에서 실내 스포츠 게임 즐기기 (14:00~16:00)\n" +
                        "\n" +
                        "VR과 AR로 즐기는 실내 스포츠 테마파크에서 가족이나 친구와 함께 다양한 게임을 체험합니다.\n" +
                        "\n" +
                        "고고스 카페에서 커피 타임 (16:30~17:30)\n" +
                        "커피 한 잔과 함께 여유로운 오후를 즐기며 여행을 마무리합니다.")
                .build();
        scheduleRepo.save(tour1SecondDay);
        tourPack1.getSchedules().add(tour1FirstDay);
        tourPack1.getSchedules().add(tour1SecondDay);
    }

    public void package2(){
        Package tourPack2 = Package.builder()
                // DDP,남산서울타워,국립현대미술관 덕수궁,간송미술관(서울 보화각),가쯔야,기와,광화문석갈비,호텔
                .listTourists("2470006,126535,130173,130511,2652903,2029960,2670606,2652993,금동화로숯불구이,3337233")
                .price(300000)
                .title("서울의 예술과 전통을 담은 1박 2일 도심 여행")
                .build();
        packageRepo.save(tourPack2);
        Schedule tour2FirstDay = Schedule.builder()
                .tourPackage(tourPack2)
                .day("Day1")
                .mainActivity("DDP,국립현대미술관 덕수궁관, 간송미술관")
                .detail("오전\n" +
                        "\n" +
                        "DDP(동대문 디자인 플라자) 탐방 (09:00~11:00)\n" +
                        "서울의 현대적 랜드마크인 *동대문 디자인 플라자(DDP)*에서 독특한 건축미를 감상하고, 진행 중인 다양한 전시회를 둘러봅니다. 예술과 디자인의 조화를 느낄 수 있는 공간입니다.\n" +
                        "점심 2. 가쯔야에서 점심식사 (11:30~12:30)\n" +
                        "\n" +
                        "일본식 정통 돈카츠 전문점 가쯔야에서 바삭하고 촉촉한 돈카츠와 정통 일본 요리를 맛보며 든든한 식사를 즐깁니다.\n" +
                        "오후 3. 국립현대미술관 덕수궁관 탐방 (13:00~15:00)\n" +
                        "\n" +
                        "서울 도심 속에서 한국의 근대 미술을 만나볼 수 있는 국립현대미술관 덕수궁관에서 다양한 현대 미술 작품과 전시를 감상합니다. 근대 역사 속 예술적 흔적을 발견할 수 있는 흥미로운 시간이 될 것입니다.\n" +
                        "\n" +
                        "간송미술관(서울 보화각) 탐방 (15:30~17:00)\n" +
                        "한국 전통 예술의 보물창고인 간송미술관에서 한국의 문화재와 예술 작품을 감상합니다. 보화각은 전통 건축물로, 전통과 예술이 어우러진 독특한 공간입니다.\n" +
                        "저녁 5. 광화문석갈비에서 저녁식사 (18:00~19:30)\n" +
                        "\n" +
                        "한우와 갈비가 유명한 광화문석갈비에서 고급스러운 한식을 즐기며 서울 도심 속에서 전통적인 식사를 경험합니다." +
                        "\n" +
                        "숙박 6. 노보텔 앰배서더 서울 동대문 호텔 & 레지던스에서 수박 (20:00~)")
                .build();
        scheduleRepo.save(tour2FirstDay);
        Schedule tour2SecondDay = Schedule.builder()
                .tourPackage(tourPack2)
                .day("Day1")
                .mainActivity("남산서울타워 덕수궁, 정동길, 광화문 광장.")
                .detail("아침\n" +
                        "\n" +
                        "호텔 조식 및 체크아웃 (08:00~09:00)\n" +
                        "호텔에서 조식을 즐기고, 다음 일정을 준비합니다.\n" +
                        "오전 2. 남산서울타워 방문 (09:30~11:30)\n" +
                        "\n" +
                        "남산서울타워에 올라가 서울의 전경을 한눈에 내려다보세요. 케이블카를 타고 올라가는 과정도 즐거운 경험이 될 것입니다. 이곳에서 서울의 아름다운 도심 풍경을 느낄 수 있습니다.\n" +
                        "점심 3. 기와에서 점심식사 (12:00~13:30)\n" +
                        "\n" +
                        "한식 전문점 기와에서 정갈한 한식을 즐기며 전통의 맛을 느껴보세요. 한옥 느낌의 분위기 속에서 편안한 식사 시간을 보낼 수 있습니다.\n" +
                        "오후 4. 덕수궁 산책 및 정동길 탐방 (14:00~15:30)\n" +
                        "\n" +
                        "고요한 정원을 가진 덕수궁을 산책하며 고궁의 고즈넉한 분위기를 만끽하세요. 이후 주변의 정동길을 걸으며 서울 속 숨겨진 감성적인 공간들을 둘러볼 수 있습니다.\n" +
                        "마무리 5. 광화문 광장 자유 시간 (16:00~17:00)\n" +
                        "\n" +
                        "광화문 광장에서 자유롭게 산책하거나 기념사진을 찍으며 여행을 마무리합니다.")
                .build();

        scheduleRepo.save(tour2SecondDay);
        tourPack2.getSchedules().add(tour2FirstDay);
        tourPack2.getSchedules().add(tour2SecondDay);
    }

    public void package3(){
        Package tourPack3 = Package.builder()
                //남산골한옥마을,강남,창덕궁과 후원,광화문,옛맛서울불고기,갓덴스시 강남,감꽃당,개뿔,롯데호텔 L7 강남
                .listTourists("126747,264570,127642,126512,2642516,2685274,2622715,2848108,2548198")
                .price(500000)
                .title("서울 전통과 현대를 잇는 1박 2일 여행 코스")
                .build();
        packageRepo.save(tourPack3);
        Schedule tour3FirstDay = Schedule.builder()
                .tourPackage(tourPack3)
                .day("Day1")
                .mainActivity("남산골한옥마을, 옛맛서울불고기, 창덕궁과 후원, 감꽃당.")
                .detail("첫째 날: 전통의 매력 속으로\n" +
                        "오전\n" +
                        "\n" +
                        "남산골한옥마을 탐방 (09:00~12:00)\n" +
                        "서울의 중심에서 한국 전통 가옥을 체험할 수 있는 남산골한옥마을을 방문하여 한옥의 아름다움을 느끼고, 전통 놀이와 문화 체험을 즐깁니다.\n" +
                        "점심 2. 옛맛서울불고기에서 점심 (12:30~13:30)\n" +
                        "\n" +
                        "전통 불고기 맛집인 옛맛서울불고기에서 불고기의 깊은 맛을 체험합니다. 한국식 소불고기의 담백하고 달콤한 풍미를 느낄 수 있습니다.\n" +
                        "오후 3. 창덕궁과 후원 탐방 (14:00~17:00)\n" +
                        "\n" +
                        "유네스코 세계유산으로 지정된 창덕궁과 후원을 탐방하며, 조선 왕조의 궁궐과 그 후원을 산책합니다. 특히 후원의 자연과 조화를 이룬 아름다움은 꼭 경험해야 할 명소입니다.\n" +
                        "\n" +
                        "저녁 5. 감꽃당에서 전통 한식 저녁식사 (18:00~19:30)\n" +
                        "\n" +
                        "정갈하고 품격 있는 한식 레스토랑 감꽃당에서 전통적인 한식을 즐기며 하루의 피로를 풀어보세요. 한국의 사계절을 담은 음식들이 제공됩니다.\n" +
                        "숙박 6. 롯데호텔 L7 강남 체크인 및 휴식 (20:00~)\n" +
                        "\n" +
                        "롯데호텔 L7 강남에서 편안하게 숙박하며, 강남의 중심에서 여유로운 시간을 보낼 수 있습니다.")
                .build();
        scheduleRepo.save(tour3FirstDay);
        Schedule tour3SecondDay = Schedule.builder()
                .tourPackage(tourPack3)
                .day("Day2")
                .mainActivity("강남 거리 탐방, 갓덴스시 강남, 개뿔 카페 , 광화문 탐방")
                .detail("둘째 날: 현대적 감각과 미식 탐방\n" +
                        "아침\n" +
                        "\n" +
                        "호텔에서 조식 및 체크아웃 (08:00~09:00)\n" +
                        "L7 호텔에서 조식을 즐기고, 강남의 현대적인 분위기를 만끽하며 아침을 시작합니다.\n" +
                        "오전 2. 광화문 탐방 (09:30~11:30)\n" +
                        "\n" +
                        "광화문광장에서 이순신 장군 동상과 세종대왕 동상을 감상하고, 주변의 역사적 명소들을 돌아봅니다. 서울 도심의 역사적 중심지에서 서울의 전통과 현대를 동시에 느껴보세요.\n" +
                        "점심 3. 갓덴스시 강남에서 점심식사 (12:00~13:30)\n" +
                        "\n" +
                        "신선한 스시를 제공하는 갓덴스시 강남점에서 점심을 즐깁니다. 퀄리티 높은 재료로 만든 다양한 종류의 스시를 맛볼 수 있습니다.\n" +
                        "오후 4. 개뿔 카페에서 여유로운 티타임 (14:00~15:30)\n" +
                        "\n" +
                        "독특한 이름으로 유명한 개뿔 카페에서 분위기 있는 시간을 보내며 커피나 디저트를 즐기세요. 강남의 현대적인 감각이 묻어나는 힙한 공간입니다.\n" +
                        "마무리 5. 강남 거리 탐방 및 쇼핑 (16:00~17:00)\n" +
                        "\n" +
                        "강남의 세련된 거리와 트렌디한 상점들을 둘러보며 자유롭게 쇼핑을 즐깁니다. 카페에서 커피 한 잔과 함께 강남의 현대적이고 활기찬 분위기를 느껴보세요.")
                .build();

        scheduleRepo.save(tour3SecondDay);
        tourPack3.getSchedules().add(tour3FirstDay);
        tourPack3.getSchedules().add(tour3SecondDay);
    }

}
