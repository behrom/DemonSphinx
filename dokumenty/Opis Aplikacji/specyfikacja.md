# Ogólny zarys aplikacji

Aplikacja umożliwia sterowanie za pomocą głosu urządzeniem mobilnym działającym pod kontrolą systemu operacyjnego Android oraz, opcjonalnie, połączonym z nim komputerem stacjonarnym. Po uruchomieniu aplikacji na urządzeniu mobilnym działa ona w tle, czekając na wypowiedzenie przez użytkownika słowa-klucza. Po rozpoznaniu słowa-klucza aplikacja rejestruje słowa wypowiedziane przez użytkownika i przetwarza je na tekst, który następnie jest interpretowany jako odpowiednie polecenie do wykonania. Wypowiedziane polecenie może składać się z komendy oraz opcjonalnych parametrów. W zależności od wypowiedzianego przez użytkownika słowa-klucza polecenie jest wykonywane na urządzeniu mobilnym lub na urządzeniu stacjonarnym.

## Przykładowe polecenia

* *komputer włącz Chrome* - uruchomienie przeglądarki Google Chrome na urządzeniu stacjonarnym;
* *komputer wyłącz* - wyłączenie komputera;
* *telefon głośniej* - podgłośnienie telefonu;
* *telefon zadzwoń nazwa_kontaktu* - telefon wykonuje połączenie do kontaktu o podanej nazwie.

# Struktura systemu

System składa się z czterech modułów:

1. Demon działający na telefonie z systemem Android:

  * oczekuje na wypowiedzenie słowa klucza i rozpoznaje jego wypowiedzenie;
  * po rozpoznaniu słowa-klucza rejestruje dźwięk i za pomocą systemu konwersji mowy na tekst dostarczanego przez Google konwertuje wypowiedziane przez użytkownika słowa na tekst;
  * interpretuje uzyskany tekst jako polecenie do wykonania;
  * w zależności od tego, czy polecenie ma zostać wykonane na urządzeniu mobilnym czy stacjonarnym, tworzy proces potomny wykonujący to polecenie lub przesyła informację do serwera działającego na urządzeniu stacjonarnym.

2. GUI do konfiguracji aplikacji na telefonie z systemem Android:

  * umożliwia uruchomienie oraz wyłączenie aplikacji;
  * pozwala na intuicyjne dostosowywanie istniejących poleceń do potrzeb użytkownika oraz dodawanie nowych poleceń.

3. Serwer na urządzeniu stacjonarnym:

  * oczekuje na nadejście z urządzenia mobilnego polecenia do wykonania;
  * po otrzymaniu polecenia tworzy proces potomny wykonujący to polecenie.

4. GUI do konfiguracji serwera na urządzeniu stacjonarnym:

  * ... do czego służy? ...

# Wymagania

## Przykładowe czynności, jakie obsługuje aplikacja:

* wyłączenie telefonu/komputera
* zwiększenie/zmniejszenie głośności na telefonie (multimediów, alarmów, dzwonka)
* zwiększenie/zmniejszenie głośności na komputerze
* zwiększenie/zmniejszenie jasności ekranu (komputer, telefon) 
* zadzwoń do osoby o zadanej nazwie kontaktu (telefon)
* następny utwór/strona (komputer/telefon)

# Uwagi dotyczące implementacji
## Serwer na komputerze

* działa w tle;
* odbiera polecenia przez internet na zadanym porcie;
* wykorzystuje TCP.

##  Demon na telefonie

* działa w tle i aktywuje się po nadejściu słowa klucz **KOMPUTER** lub **TELEFON**
  * słowa klucz KOMPUTER lub TELEFON są przetwarzane przy wykorzystaniu biblioteki SPHINX. Diagram przepływu – diagram nr 1
* gdy nadejdzie słowo klucz, demon przy wykorzystaniu połączenia z serwerami Google konwertuje polecenie oraz ewentualne parametry z postaci głosowej na tekstową (speech to text). Diagram przepływu –  diagram nr 2 i diagram nr 3
* następnie, w zależności od tego, czy dana czynność ma zostać wykonana na komputerze czy na telefonie, tworzy odpowiednie wątki odpowiedzialne za wykonanie polecenia lub przesłanie stosownego komunikatu do serwera na komputerze. Diagram przepływu – diagram nr 2 i diagram nr 3

## GUI do konfiguracji na telefonie
* przycisk do wyłączenia/włączenia demona
* konfiguracja poleceń i słów przypisanych do nich
* ewentualna konfiguracja połączenia z komputerem