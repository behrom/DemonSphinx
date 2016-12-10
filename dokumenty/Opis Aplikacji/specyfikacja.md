# Ogólny zarys aplikacji
Użytkownik za pomocą słowa klucza wybiera, czy polecenie ma zostać wykonane przez telefon czy komputer, następnie podaje polecenie w formie komenda + ewentualne parametry. Następnie w zależności od tego, czy czy aplikacja ma wykonać polecenie na telefonie czy na komputerze, uruchamia odpowiednie procedury (zob. diagram 2 oraz diagram 3).

## Przykłady wywołań:
* *komputer włącz Chrome* - uruchomienie Chrome;
* *komputer wyłącz* - wyłączenie komputera;
* *telefon głośniej* - podgłośnienie telefonu;
* *telefon zadzwoń nazwa_kontaktu* - telefon wykonuje połączenie do kontaktu o podanej nazwie.

## Przykładowe czynności, jakie obsługuje aplikacja:
* wyłączenie telefonu/komputera
* zwiększenie/zmniejszenie głośności na telefonie (multimediów, alarmów, dzwonka)
* zwiększenie/zmniejszenie głośności na komputerze
* zwiększenie/zmniejszenie jasności ekranu (komputer, telefon) 
* zadzwoń do osoby o zadanej nazwie kontaktu (telefon)
* następny utwór/strona (komputer/telefon)

# Struktura systemu
## Serwer na komputerze stacjonarnym

Nasłuchuje, czekając na nadejście z urządzenia mobilnego polecenia, jakie ma zostać wykonane.

## Demon na telefonie z systemem Android

Czeka na nadejście polecenia. Po otrzymaniu polecenia tworzy procesy potomne, komunikujące się z serwerem na urządzeniu stacjonarnym lub wykonujące odpowiednie akcje na urządzeniu z systemem Android.

## GUI do konfiguracji aplikacji na urządzeniu z systemem Android

## GUI do konfiguracji serwera na komputerze stacjonarnym

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