# Ustalenia

- sporządzanie protokołów z każdego spotkania; umieszczanie ich na repo w wersji źródłowej i w wersji HTML na stronie
- protokoły ze spotkań, dokumentacja - źródła w formacie Markdown (zob. https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet), na stronie wersja HTML (automatyczna konwersja)
- komunikacja elektroniczna z opiekunem zespołu
  - e-mail [proszę o e-maile do wszystkich]
  - dokumenty - umieszczamy na git
  - trac
  	- wiki
  	- zgłoszenia
  - pilne sprawy: 691 120 670
- cotygodniowy raport (stały termin - jaki?) obejmujący:
  - stan postępów prac,
  - napotkane problemy,
  - inne sprawy.
- nie jest dla mnie jasne, co ma zawierać dokumentacja projektu - dokumentacja techniczna, *dokumentacja użytkownika* (chyba tak), dokumentacja przebiegu prac zespołu?
- "Zespół (sekretarz zespołu) ma obowiązek gromadzić całą korespondencję związaną z projektem, udostępniać ją opiekunowi i przechowywać ją do dnia zaliczenia projektu." - wniosek: maile DW sekretarza (a może DW wspólnej skrzynki)
- rozpoznawanie mowy:
  - czy to nie jest wbudowane w Androida? https://support.google.com/websearch/answer/2940021?co=GENIE.Platform%3DAndroid&hl=en ; http://io2015codelabs.appspot.com/codelabs/voice-interaction#1 ; http://www.howtogeek.com/139413/16-android-voice-actions-to-make-android-your-own-personal-assistant/
  - ogólniej - Google ma rozwiązanie płatne: https://cloud.google.com/speech/
  - via Chrome API? (chyba słaby pomysł)
  - systemy rozpoznawania mowy open source - trudne zagadnienie; konieczność budowania modeli; nie dla nas
  - Microsoft Bing Speech API - 5K calls per month for free (https://www.microsoft.com/cognitive-services/en-us/pricing)
  - *wniosek:* może warto skorzystać z rozwiązania wbudowanego w Androida, ale skupić się na części "klient-serwer" lub zmienić projekt (rozpoznawanie liści lub projektowanie GUI)
- wymodelowanie schematu działania aplikacji - czy wiedzą jak?
  - diagramy (pseudo-)UML; polecam: http://plantuml.com/
  - podział na moduły - interfejsy między modułami
  - równoległy rozwój modułów
  - wybranie technologii po zamodelowaniu aplikacji -- a co jeśli technologii nie ma, a sami nie jesteśmy w stanie ich wytworzyc? praca przy modelowaniu na marne

# Harmonogram

- zrównoleglić (diagram Gantta)
  - https://pl.wikipedia.org/wiki/Diagram_Gantta
- uwzględnić w harmonogramie:
  - prace nad harmonogramem/planem pracy
  - przygotowanie prezentacji projektu
  - przydział zadań
- uwzględnić terminy z regulaminu
  - tworzenie harmonogramu i planu pracy (do 11.12.2016)
  - 06.02.2017 - przedłożenie koordynatorom:
  	- raportu kierownika zespołu
  	- indywidualne raporty poszczególnych członków
  - 09.02.2017 - ostateczny termin nieplanowanej "zmiany ról" w zespole
  - 07.05.2017 - zakończenie pracy nad projektem i umieszczenie pełnej dokumentacji projektu na stronie WWW
  - 14.05.2017 - przygotowanie prezentacji projektu
- harmonogram i plan pracy mają zawierać:
  - harmonogram (w formie diagramu Gantta)
  - opis zadań zleconych poszczególnym członkom zespołu
  - określenie zasad dokumentacji przebiegu realizacji zadania

# TODO

- *(???)* administacja Trac'iem
  - [do 5.12.2016] jak się do tego dobrać?
  - [do 8.12.2016] uwzględnić podział na moduły (komponenty); poza modułami programu uwzględnić sprawy organizacyjne, projektowanie, dokumentację, prezentację, testy
  - [do 14.12.2016] gdy będzie gotowy harmonogram, uwzględnić go w części "plan prac"
- *(Michał)* [do 11.12.2016] - zatwierdzić ostateczną wersję harmonogramu i planu pracy
  - *(zespół)* - przygotowanie poprawionego harmonogramu
  - *(Hubert)* [do 6.12.2016] - wersja poprawiona harmonogramu przesłana do Michała i umieszczona na repozytorium
- *(Katarzyna)* protokół z poprzedniego spotkania umieścić na stronie i repo (dokumenty/spotkania/YYYY_MM_DD/protokol.md)
- *(Michał, Sebastian(?))* [do 5.12.2016] ustalenie wymagań dotyczących projektu
- *(Michał)* [do 4.12.2016] ustalić co powinna zawierać dokumentacja projektu - czy to dokumentacja użytkownika czy dokumentacja techniczna, dokumentacja przebiegu prac zespołu?
- *(Katarzyna, Hubert)* [do 5.12.2016] "Zespół (sekretarz zespołu) ma obowiązek gromadzić całą korespondencję związaną z projektem, udostępniać ją opiekunowi i przechowywać ją do dnia zaliczenia projektu." - zgromadzić dotychczasową korespondencję; na jakiej zasadzie będzie ona udostępniana?
- *(Michał)* [do 2.12.2016] zorganizować salę na poniedziałkowe spotkania; dać znać Hubertowi skąd wziąć klucz
- *(zespół)* - praca nad modelem aplikacji
- *(zespół)* - wyszukanie informacji o technologiach możliwych do użycia