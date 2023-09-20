package br.com.alura.forum.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files

@RestController
class ImagemController {

    @GetMapping("/imagem")
    fun getImagem (response: HttpServletResponse): ResponseEntity<ByteArray>{
        val caminho = "C:\\Users\\jluca\\OneDrive\\Documents\\GitHub\\forum\\src\\main\\kotlin\\br\\com\\alura\\forum\\imagens\\image.jpg"
        val imagem = File(caminho)
        val imagemBytes = Files.readAllBytes(imagem.toPath())

        response.contentType = MediaType.IMAGE_JPEG_VALUE
        val output = response.outputStream
        output.write(imagemBytes)
        output.close()

        return ResponseEntity.ok().build()
    }

}