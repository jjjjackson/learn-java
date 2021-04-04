package main

import (
	"fmt"
	"math/rand"
	"os"
	"path/filepath"
	"strconv"
)

func main() {

	for i := 101; i < 106; i++ {
		createDummyProduct(i)
		createDummyReview(i)
	}
}

func createDummyProduct(i int) {
	wd, err := os.Getwd()
	if err != nil {
		panic(err)
	}
	location := wd + "/src/labs/data/"
	fp := filepath.Join(location, "product"+strconv.Itoa(i)+".csv")
	fmt.Println(fp)

	f, err := os.Create(fp)
	if err != nil {
		panic(err)
	}

	defer f.Close()

	randPrice := rand.Float32() * 100
	randReview := rand.Intn(5)
	strToFile := fmt.Sprintf("D,%d,English Breakfast Tea,%.2f,%d,2020-06-17\n", i, randPrice, randReview)

	_, err = f.WriteString(strToFile)
	if err != nil {
		panic(err)
	}

	f.Sync()
}

func createDummyReview(i int) {
	wd, err := os.Getwd()
	if err != nil {
		panic(err)
	}
	location := wd + "/src/labs/data/"
	fp := filepath.Join(location, "reviews"+strconv.Itoa(i)+".csv")
	fmt.Println(fp)

	f, err := os.Create(fp)
	if err != nil {
		panic(err)
	}

	defer f.Close()

	randNumOfReview := rand.Intn(5) + 1

	for j := 0; j < randNumOfReview; j++ {
		randReview := rand.Intn(5)
		strToFile := fmt.Sprintf("%d,This is the best one I ever had\n", randReview)

		_, err := f.WriteString(strToFile)
		if err != nil {
			panic(err)
		}
	}
	f.Sync()
}
